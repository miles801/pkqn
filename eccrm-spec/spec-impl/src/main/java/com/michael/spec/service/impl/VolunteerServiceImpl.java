package com.michael.spec.service.impl;

import com.michael.spec.bo.VolunteerBo;
import com.michael.spec.dao.VolunteerDao;
import com.michael.spec.domain.Volunteer;
import com.michael.spec.service.VolunteerService;
import com.michael.spec.vo.VolunteerVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import eccrm.base.parameter.service.ParameterContainer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService, BeanWrapCallback<Volunteer, VolunteerVo> {
    @Resource
    private VolunteerDao volunteerDao;

    @Override
    public String save(Volunteer volunteer) {
        ValidatorUtils.validate(volunteer);
        String id = volunteerDao.save(volunteer);
        return id;
    }

    @Override
    public void update(Volunteer volunteer) {
        ValidatorUtils.validate(volunteer);
        volunteerDao.update(volunteer);
    }

    @Override
    public PageVo pageQuery(VolunteerBo bo) {
        PageVo vo = new PageVo();
        Long total = volunteerDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<Volunteer> volunteerList = volunteerDao.query(bo);
        List<VolunteerVo> vos = BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(volunteerList, VolunteerVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public VolunteerVo findById(String id) {
        Volunteer volunteer = volunteerDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(volunteer, VolunteerVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            volunteerDao.deleteById(id);
        }
    }


    @Override
    public void doCallback(Volunteer volunteer, VolunteerVo vo) {
        ParameterContainer container = ParameterContainer.getInstance();
        // 性别
        vo.setSexName(container.getBusinessName("BP_SEX", volunteer.getSex()));

    }
}
