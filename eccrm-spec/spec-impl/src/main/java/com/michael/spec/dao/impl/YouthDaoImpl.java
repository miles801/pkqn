package com.michael.spec.dao.impl;

import com.michael.spec.bo.YouthBo;
import com.michael.spec.dao.YouthDao;
import com.michael.spec.domain.Youth;
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
@Repository("youthDao")
public class YouthDaoImpl extends HibernateDaoHelper implements YouthDao {

    @Override
    public String save(Youth youth) {
        return (String) getSession().save(youth);
    }

    @Override
    public void update(Youth youth) {
        getSession().update(youth);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Youth> query(YouthBo bo) {
        Criteria criteria = null;
        if (bo != null && bo.isNoPermission()) {
            criteria = createCriteria(Youth.class);
        } else {
            criteria = createCriteria(Youth.class, "YOUTH_FILTER", "creatorId", FilterFieldType.EMPLOYEE);
        }
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(YouthBo bo) {
        Criteria criteria = null;
        if (bo != null && bo.isNoPermission()) {
            criteria = createRowCountsCriteria(Youth.class);
        } else {
            criteria = createRowCountsCriteria(Youth.class, "YOUTH_FILTER", "creatorId", FilterFieldType.EMPLOYEE);
        }
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Youth.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Youth youth) {
        Assert.notNull(youth, "要删除的对象不能为空!");
        getSession().delete(youth);
    }

    @Override
    public Youth findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Youth) getSession().get(Youth.class, id);
    }

    private void initCriteria(Criteria criteria, YouthBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}