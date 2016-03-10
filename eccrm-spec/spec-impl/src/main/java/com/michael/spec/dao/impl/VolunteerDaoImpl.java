package com.michael.spec.dao.impl;

import com.michael.spec.bo.VolunteerBo;
import com.michael.spec.dao.VolunteerDao;
import com.michael.spec.domain.Volunteer;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.core.hibernate.filter.FilterFieldType;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("volunteerDao")
public class VolunteerDaoImpl extends HibernateDaoHelper implements VolunteerDao {

    @Override
    public String save(Volunteer volunteer) {
        return (String) getSession().save(volunteer);
    }

    @Override
    public void update(Volunteer volunteer) {
        getSession().update(volunteer);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Volunteer> query(VolunteerBo bo) {
        Criteria criteria = createCriteria(Volunteer.class, "PD_VOLUNTEER", "ownerId", FilterFieldType.EMPLOYEE);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(VolunteerBo bo) {
        Criteria criteria = createRowCountsCriteria(Volunteer.class, "PD_VOLUNTEER", "ownerId", FilterFieldType.EMPLOYEE);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Volunteer.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Volunteer volunteer) {
        Assert.notNull(volunteer, "要删除的对象不能为空!");
        getSession().delete(volunteer);
    }

    @Override
    public Volunteer findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Volunteer) getSession().get(Volunteer.class, id);
    }

    private void initCriteria(Criteria criteria, VolunteerBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}