package com.michael.spec.dao;

import com.michael.spec.bo.YouthRelationBo;
import com.michael.spec.domain.YouthRelation;

import java.util.List;

/**
 * @author Michael
 */
public interface YouthRelationDao {

    String save(YouthRelation youthRelation);

    void update(YouthRelation youthRelation);

    /**
     * 高级查询接口
     */
    List<YouthRelation> query(YouthRelationBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(YouthRelationBo bo);

    YouthRelation findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(YouthRelation youthRelation);

    /**
     * 查询指定闲散青年的家庭关系
     *
     * @param youthId 闲散青年ID
     * @return 家庭关系
     */
    List<YouthRelation> findByYouth(String youthId);

    /**
     * 删除指定闲散青年的所有家庭关系
     *
     * @param youthId 闲散青年ID
     */
    void deleteByYouth(String youthId);
}
