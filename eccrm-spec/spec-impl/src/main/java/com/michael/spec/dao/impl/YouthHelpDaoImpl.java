package com.michael.spec.dao.impl;

import com.michael.spec.bo.YouthHelpBo;
import com.michael.spec.dao.YouthHelpDao;
import com.michael.spec.domain.YouthHelp;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("youthHelpDao")
public class YouthHelpDaoImpl extends HibernateDaoHelper implements YouthHelpDao {

    @Override
    public String save(YouthHelp youthHelp) {
        return (String) getSession().save(youthHelp);
    }

    @Override
    public void update(YouthHelp youthHelp) {
        getSession().update(youthHelp);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<YouthHelp> query(YouthHelpBo bo) {
        Criteria criteria = createCriteria(YouthHelp.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(YouthHelpBo bo) {
        Criteria criteria = createRowCountsCriteria(YouthHelp.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + YouthHelp.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(YouthHelp youthHelp) {
        Assert.notNull(youthHelp, "要删除的对象不能为空!");
        getSession().delete(youthHelp);
    }

    @Override
    public void deleteByYouth(String youthId) {
        Assert.hasText(youthId, "删除帮扶记录失败!没有获得青年ID!");
        getSession().createQuery("delete from " + YouthHelp.class.getName() + " yh where yh.youthId=?")
                .setParameter(0, youthId)
                .executeUpdate();
    }

    @Override
    public YouthHelp findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (YouthHelp) getSession().get(YouthHelp.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> workMonthReport(int year, int month) {
        return getSession().createSQLQuery("select y.owner_name,e.duty,y.name,y.sex,y.age,y.mobile,h.title,y.y_state,h.OCCUR_DATE from spec_youth y join spec_youth_help h on y.id=h.YOUTH_ID join sys_emp e on y.OWNER_ID=e.id " +
                "where year(h.OCCUR_DATE)=? and month(h.OCCUR_DATE)=?")
                .setParameter(0, year)
                .setParameter(1, month)
                .list();
    }

    private void initCriteria(Criteria criteria, YouthHelpBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}