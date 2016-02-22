package com.michael.spec.service.impl;

import com.michael.spec.bo.CondoleBo;
import com.michael.spec.dao.CondoleDao;
import com.michael.spec.dao.PoorTeenagersDao;
import com.michael.spec.domain.Condole;
import com.michael.spec.domain.PoorTeenagers;
import com.michael.spec.service.CondoleService;
import com.michael.spec.vo.CondoleVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("condoleService")
public class CondoleServiceImpl implements CondoleService {
    @Resource
    private CondoleDao condoleDao;

    @Resource
    private PoorTeenagersDao poorTeenagersDao;

    @Override
    public String save(Condole condole) {
        ValidatorUtils.validate(condole);
        // 设置机构
        PoorTeenagers poorTeenagers = poorTeenagersDao.findById(condole.getPoorTeenagerId());
        Assert.notNull(poorTeenagers, "数据错误：贫困青年[" + condole.getPoorTeenagerId() + "]不存在!");
        condole.setPoorTeenagerName(poorTeenagers.getName());
        condole.setOrgId(poorTeenagers.getOrgId());
        condole.setOrgName(poorTeenagers.getOrgName());
        String id = condoleDao.save(condole);
        resetTeenagerCondole(condole.getPoorTeenagerId(), poorTeenagers);
        return id;
    }

    private void resetTeenagerCondole(String teenagerId, PoorTeenagers poorTeenagers) {
        Assert.hasText(teenagerId, "重置慰问次数时：青年ID不能为空!");
        if (poorTeenagers == null) {
            poorTeenagers = poorTeenagersDao.findById(teenagerId);
            Assert.notNull(poorTeenagers, "数据错误：贫困青年[" + teenagerId + "]不存在!");
        }
        // 查询慰问次数
        int counts = condoleDao.condoleCounts(teenagerId);
        poorTeenagers.setCondoleTimes(counts);

        // 查询总金额
        if (counts == 0) {
            poorTeenagers.setCondoleMoney(0d);
        } else {
            Double money = condoleDao.condoleMoney(teenagerId);
            poorTeenagers.setCondoleMoney(money);
        }

    }

    @Override
    public void update(Condole condole) {
        ValidatorUtils.validate(condole);
        condoleDao.update(condole);
        resetTeenagerCondole(condole.getPoorTeenagerId(), null);
    }

    @Override
    public PageVo pageQuery(CondoleBo bo) {
        PageVo vo = new PageVo();
        Long total = condoleDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<Condole> condoleList = condoleDao.query(bo);
        List<CondoleVo> vos = BeanWrapBuilder.newInstance()
                .wrapList(condoleList, CondoleVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public CondoleVo findById(String id) {
        Condole condole = condoleDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(condole, CondoleVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            // 查询并删除
            Condole condole = condoleDao.findById(id);
            String poorTeenagerId = condole.getPoorTeenagerId();
            condoleDao.delete(condole);
            // 重置数量
            resetTeenagerCondole(poorTeenagerId, null);
        }
    }

    @Override
    public List<Object[]> analysisCondole(int year) {
        return condoleDao.analysisCondole(year);
    }

    @Override
    public List<CondoleVo> queryByTeenager(String teenagerId) {
        Assert.hasText(teenagerId, "参数错误：缺少参数ID!");
        CondoleBo bo = new CondoleBo();
        bo.setPoorTeenagerId(teenagerId);
        List<Condole> condoles = condoleDao.query(bo);
        return BeanWrapBuilder.newInstance().wrapList(condoles, CondoleVo.class);
    }
}
