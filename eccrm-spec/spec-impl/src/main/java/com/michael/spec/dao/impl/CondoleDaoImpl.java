package com.michael.spec.dao.impl;

import com.michael.spec.bo.CondoleBo;
import com.michael.spec.dao.CondoleDao;
import com.michael.spec.domain.Condole;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("condoleDao")
public class CondoleDaoImpl extends HibernateDaoHelper implements CondoleDao {

    @Override
    public String save(Condole condole) {
        return (String) getSession().save(condole);
    }

    @Override
    public void update(Condole condole) {
        getSession().update(condole);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Condole> query(CondoleBo bo) {
        Criteria criteria = createCriteria(Condole.class);
        initCriteria(criteria, bo);
        criteria.addOrder(Order.desc("occurDate"));
        return criteria.list();
    }

    @Override
    public Long getTotal(CondoleBo bo) {
        Criteria criteria = createRowCountsCriteria(Condole.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + Condole.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Condole condole) {
        Assert.notNull(condole, "要删除的对象不能为空!");
        getSession().delete(condole);
    }

    @Override
    public Condole findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (Condole) getSession().get(Condole.class, id);
    }

    @Override
    public int condoleCounts(String poorTeenagersId) {
        Assert.hasText(poorTeenagersId, "缺少参数：贫困青年ID不能为空!");
        return Integer.parseInt(createRowCountsCriteria(Condole.class)
                .add(Restrictions.eq("poorTeenagerId", poorTeenagersId))
                .uniqueResult() + "");
    }

    @Override
    public Double condoleMoney(String poorTeenagersId) {
        Assert.hasText(poorTeenagersId, "缺少参数：贫困青年ID不能为空!");
        return (Double) createCriteria(Condole.class)
                .setProjection(Projections.sum("money"))
                .add(Restrictions.eq("poorTeenagerId", poorTeenagersId))
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> analysisCondole(int year) {
        return getSession().createQuery("select c.orgId,c.orgName,count(c.id),sum(c.money) from " + Condole.class.getName()
                + " c where YEAR(c.occurDate)=:year group by c.orgId,c.orgName ")
                .setParameter("year", year)
                .list();
    }

    private void initCriteria(Criteria criteria, CondoleBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}