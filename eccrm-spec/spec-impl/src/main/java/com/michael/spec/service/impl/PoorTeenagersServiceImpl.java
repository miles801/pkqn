package com.michael.spec.service.impl;

import com.michael.spec.bo.PoorTeenagersBo;
import com.michael.spec.dao.CondoleDao;
import com.michael.spec.dao.PoorTeenagersDao;
import com.michael.spec.domain.PoorTeenagers;
import com.michael.spec.service.PoorTeenagersService;
import com.michael.spec.vo.PoorTeenagersVo;
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
@Service("poorTeenagersService")
public class PoorTeenagersServiceImpl implements PoorTeenagersService, BeanWrapCallback<PoorTeenagers, PoorTeenagersVo> {
    @Resource
    private PoorTeenagersDao poorTeenagersDao;

    @Resource
    private CondoleDao condoleDao;

    @Override
    public String save(PoorTeenagers poorTeenagers) {
        ValidatorUtils.validate(poorTeenagers);
        setAge(poorTeenagers);
        poorTeenagers.setCondoleTimes(0);
        poorTeenagers.setCondoleMoney(0d);
        String id = poorTeenagersDao.save(poorTeenagers);
        return id;
    }

    private void setAge(PoorTeenagers poorTeenagers) {
        Date date = poorTeenagers.getBirthday();
        if (date != null) {
            poorTeenagers.setAge(Integer.parseInt((System.currentTimeMillis() - date.getTime()) / (1000 * 60 * 60 * 24 * 365l) + ""));
        }
    }

    @Override
    public void update(PoorTeenagers poorTeenagers) {
        ValidatorUtils.validate(poorTeenagers);
        setAge(poorTeenagers);
        poorTeenagersDao.update(poorTeenagers);
    }

    @Override
    public PageVo pageQuery(PoorTeenagersBo bo) {
        PageVo vo = new PageVo();
        Long total = poorTeenagersDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<PoorTeenagers> poorTeenagersList = poorTeenagersDao.query(bo);
        List<PoorTeenagersVo> vos = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(poorTeenagersList, PoorTeenagersVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public PoorTeenagersVo findById(String id) {
        PoorTeenagers poorTeenagers = poorTeenagersDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrap(poorTeenagers, PoorTeenagersVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            int counts = condoleDao.condoleCounts(id);
            Assert.isTrue(counts == 0, "该记录无法删除!请先删除慰问记录!");
            poorTeenagersDao.deleteById(id);
        }
    }


    @Override
    public void doCallback(PoorTeenagers poorTeenagers, PoorTeenagersVo vo) {
        ParameterContainer parameterContainer = ParameterContainer.getInstance();
        // 性别
        vo.setSexName(parameterContainer.getBusinessName("BP_SEX", poorTeenagers.getSex()));
        // 民族
        vo.setMzName(parameterContainer.getBusinessName("BP_NATION", poorTeenagers.getMz()));
        // 政治面貌
        vo.setZzmmName(parameterContainer.getBusinessName("BP_ZZMM", poorTeenagers.getZzmm()));
        // 政治面貌
        vo.setHealthName(parameterContainer.getBusinessName("BP_HEALTH", poorTeenagers.getHealth()));
        // 家庭年收入
        vo.setIncomeName(parameterContainer.getBusinessName("FAMARY_INCOME", poorTeenagers.getIncome()));
    }
}
