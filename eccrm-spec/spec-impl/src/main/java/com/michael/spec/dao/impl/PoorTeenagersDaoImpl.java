package com.michael.spec.dao.impl;

import com.michael.spec.bo.PoorTeenagersBo;
import com.michael.spec.dao.PoorTeenagersDao;
import com.michael.spec.domain.PoorTeenagers;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("poorTeenagersDao")
public class PoorTeenagersDaoImpl extends HibernateDaoHelper implements PoorTeenagersDao {

    @Override
    public String save(PoorTeenagers poorTeenagers) {
        return (String) getSession().save(poorTeenagers);
    }

    @Override
    public void update(PoorTeenagers poorTeenagers) {
        getSession().update(poorTeenagers);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PoorTeenagers> query(PoorTeenagersBo bo) {
        Criteria criteria = createCriteria(PoorTeenagers.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(PoorTeenagersBo bo) {
        Criteria criteria = createRowCountsCriteria(PoorTeenagers.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + PoorTeenagers.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(PoorTeenagers poorTeenagers) {
        Assert.notNull(poorTeenagers, "要删除的对象不能为空!");
        getSession().delete(poorTeenagers);
    }

    @Override
    public PoorTeenagers findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (PoorTeenagers) getSession().get(PoorTeenagers.class, id);
    }

    private void initCriteria(Criteria criteria, PoorTeenagersBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}