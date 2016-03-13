package com.michael.spec.service.impl;

import com.michael.spec.bo.YouthBo;
import com.michael.spec.dao.YouthDao;
import com.michael.spec.dao.YouthHelpDao;
import com.michael.spec.dao.YouthRelationDao;
import com.michael.spec.domain.Youth;
import com.michael.spec.domain.YouthRelation;
import com.michael.spec.service.YouthService;
import com.michael.spec.vo.YouthVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
@Service("youthService")
public class YouthServiceImpl implements YouthService, BeanWrapCallback<Youth, YouthVo> {
    @Resource
    private YouthDao youthDao;

    @Resource
    private YouthRelationDao youthRelationDao;

    @Resource
    private YouthHelpDao youthHelpDao;

    @Override
    public String save(Youth youth) {
        youth.setState("RED");  // 默认为红色
        youth.setHelpTimes(0);
        setAge(youth);
        ValidatorUtils.validate(youth);
        String id = youthDao.save(youth);
        List<YouthRelation> relations = youth.getRelations();
        saveRelation(id, relations, false);
        return id;
    }

    private void saveRelation(String youthId, List<YouthRelation> relations, boolean deleteOld) {
        Assert.hasText(youthId, "保存家庭关系失败：闲散青年ID不能为空!");
        if (deleteOld) {
            youthRelationDao.deleteByYouth(youthId);
        }
        if (relations == null || relations.isEmpty()) {
            return;
        }
        for (YouthRelation relation : relations) {
            relation.setYouthId(youthId);
            youthRelationDao.save(relation);
        }

    }

    private void setAge(Youth youth) {
        Date date = youth.getBirthday();
        if (date != null) {
            youth.setAge(Integer.parseInt((System.currentTimeMillis() - date.getTime()) / (1000 * 60 * 60 * 24 * 365l) + ""));
        }
    }

    @Override
    public void update(Youth youth) {
        setAge(youth);
        ValidatorUtils.validate(youth);
        youthDao.update(youth);
    }

    @Override
    public PageVo pageQuery(YouthBo bo) {
        PageVo vo = new PageVo();
        Long total = youthDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<Youth> youthList = youthDao.query(bo);
        List<YouthVo> vos = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(youthList, YouthVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public YouthVo findById(String id) {
        Youth youth = youthDao.findById(id);
        YouthVo vo = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrap(youth, YouthVo.class);
        List<YouthRelation> relations = youthRelationDao.findByYouth(id);
        vo.setRelations(relations);
        return vo;
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            // 删除家庭关系
            youthRelationDao.deleteByYouth(id);

            // 删除帮扶记录
            youthHelpDao.deleteByYouth(id);

            // 删除自身
            youthDao.deleteById(id);
        }
    }

    @Override
    public void match(String youthId, String ownerId, String ownerName) {
        Assert.hasText(youthId, "配对失败!青年ID不能为空!");
        Assert.hasText(ownerId, "配对失败!负责人ID不能为空!");
        Youth youth = youthDao.findById(youthId);
        Assert.notNull(youth, "配对失败!青年不存在，请刷新后重试!");
        boolean matched = hasMatched(ownerId);
        Assert.isTrue(!matched, "配对失败,该负责人已经被分配了相应的闲散青年!");
        youth.setOwnerId(ownerId);
        youth.setOwnerName(ownerName);
        youth.setState(Youth.STATE_MATCHED);
    }

    @Override
    public void clearOwner(String id) {
        Assert.hasText(id, "取消配对失败!青年ID不能为空!");
        Youth youth = youthDao.findById(id);
        Assert.notNull(youth, "取消配对失败!青年不存在，请刷新后重试!");
        youth.setOwnerId(null);
        youth.setOwnerName(null);
        youth.setState(Youth.STATE_NO_MATCH);
    }

    @Override
    public boolean hasMatched(String ownerId) {
        Assert.hasText(ownerId, "负责人ID不能为空!");
        YouthBo bo = new YouthBo();
        bo.setOwnerId(ownerId);
        bo.setState(Youth.STATE_MATCHED);
        Long total = youthDao.getTotal(bo);
        return total != null && total > 0;
    }


    @Override
    public void confirmFail(String youthId) {
        Assert.hasText(youthId, "接触帮扶失败!没有获得青年ID!");
        Youth youth = youthDao.findById(youthId);
        Assert.notNull(youth, "接触帮扶失败!青年不存在!请刷新后重试!");
        Assert.isTrue(Youth.STATE_MATCHED.equals(youth.getState()), "非法操作!只有“已配对”状态的数据才可以更改!");
        youth.setState(Youth.STATE_FAIL_WAIT);
    }

    @Override
    public void confirmSuccess(String youthId) {
        Assert.hasText(youthId, "接触帮扶失败!没有获得青年ID!");
        Youth youth = youthDao.findById(youthId);
        Assert.notNull(youth, "接触帮扶失败!青年不存在!请刷新后重试!");
        Assert.isTrue(Youth.STATE_MATCHED.equals(youth.getState()), "非法操作!只有“已配对”状态的数据才可以更改!");
        youth.setState(Youth.STATE_SUCCESS_WAIT);
    }

    @Override
    public void fail(String youthId) {
        Assert.hasText(youthId, "解除帮扶操作失败!没有获得青年ID!");
        Youth youth = youthDao.findById(youthId);
        Assert.notNull(youth, "解除帮扶操作失败!青年不存在!请刷新后重试!");
        Assert.isTrue(Youth.STATE_FAIL_WAIT.equals(youth.getState()), "非法操作!只有“接触帮扶-待审核”状态的数据才可以更改!");
        youth.setState(Youth.STATE_FAIL);
    }

    @Override
    public void success(String youthId) {
        Assert.hasText(youthId, "帮扶成功操作失败!没有获得青年ID!");
        Youth youth = youthDao.findById(youthId);
        Assert.notNull(youth, "帮扶成功操作失败!青年不存在!请刷新后重试!");
        Assert.isTrue(Youth.STATE_SUCCESS_WAIT.equals(youth.getState()), "非法操作!只有“帮扶成功-待审核”状态的数据才可以更改!");
        youth.setState(Youth.STATE_SUCCESS);
    }

    @Override
    public void back(String youthId, String reason) {
        Assert.hasText(youthId, "退回失败!没有获得青年ID!");
        Youth youth = youthDao.findById(youthId);
        Assert.notNull(youth, "退回失败!青年不存在!请刷新后重试!");
        Assert.isTrue(Youth.STATE_SUCCESS_WAIT.equals(youth.getState()) || Youth.STATE_FAIL_WAIT.equals(youth.getState()), "非法操作!只有“待审核”状态的数据才可以打回!");
        youth.setState(Youth.STATE_MATCHED);
        Assert.hasText(reason, "退回失败!请填写退回的理由!");
        youth.setReason(reason);
    }

    @Override
    public List<Object[]> analysis() {
        return youthDao.analysis();
    }

    @Override
    public void doCallback(Youth youth, YouthVo vo) {
        ParameterContainer parameterContainer = ParameterContainer.getInstance();
        // 性别
        vo.setSexName(parameterContainer.getBusinessName("BP_SEX", youth.getSex()));
        // 民族
        vo.setNationName(parameterContainer.getBusinessName("BP_NATION", youth.getNation()));
        // 教育程度
        vo.setEducationName(parameterContainer.getBusinessName("BP_EDU", youth.getEducation()));
        // 状态
        vo.setStateName(parameterContainer.getBusinessName("YOUTH_STATE", youth.getState()));
    }


}
