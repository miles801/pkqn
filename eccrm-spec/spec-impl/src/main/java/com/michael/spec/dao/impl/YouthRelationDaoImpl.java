package com.michael.spec.dao.impl;

import com.michael.spec.bo.YouthRelationBo;
import com.michael.spec.dao.YouthRelationDao;
import com.michael.spec.domain.YouthRelation;
import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.utils.string.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;


/**
 * @author Michael
 */
@Repository("youthRelationDao")
public class YouthRelationDaoImpl extends HibernateDaoHelper implements YouthRelationDao {

    @Override
    public String save(YouthRelation youthRelation) {
        return (String) getSession().save(youthRelation);
    }

    @Override
    public void update(YouthRelation youthRelation) {
        getSession().update(youthRelation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<YouthRelation> query(YouthRelationBo bo) {
        Criteria criteria = createCriteria(YouthRelation.class);
        initCriteria(criteria, bo);
        return criteria.list();
    }

    @Override
    public Long getTotal(YouthRelationBo bo) {
        Criteria criteria = createRowCountsCriteria(YouthRelation.class);
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public void deleteById(String id) {
        getSession().createQuery("delete from " + YouthRelation.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(YouthRelation youthRelation) {
        Assert.notNull(youthRelation, "要删除的对象不能为空!");
        getSession().delete(youthRelation);
    }

    @Override
    public YouthRelation findById(String id) {
        Assert.hasText(id, "ID不能为空!");
        return (YouthRelation) getSession().get(YouthRelation.class, id);
    }

    @Override
    public void deleteByYouth(String youthId) {
        Assert.hasText(youthId, "删除家庭关系失败：闲散青年ID不能为空!");
        getSession().createQuery("delete from " + YouthRelation.class.getName() + " r where r.youthId=?")
                .setParameter(0, youthId)
                .executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<YouthRelation> findByYouth(String youthId) {
        if (StringUtils.isEmpty(youthId)) {
            return null;
        }
        return createCriteria(YouthRelation.class)
                .add(Restrictions.eq("youthId", youthId))
                .list();
    }

    private void initCriteria(Criteria criteria, YouthRelationBo bo) {
        Assert.notNull(criteria, "criteria must not be null!");
        CriteriaUtils.addCondition(criteria, bo);
    }

}