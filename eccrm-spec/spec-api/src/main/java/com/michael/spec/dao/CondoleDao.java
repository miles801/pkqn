package com.michael.spec.dao;

import com.michael.spec.bo.CondoleBo;
import com.michael.spec.domain.Condole;

import java.util.List;

/**
 * @author Michael
 */
public interface CondoleDao {

    String save(Condole condole);

    void update(Condole condole);

    /**
     * 高级查询接口
     */
    List<Condole> query(CondoleBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(CondoleBo bo);

    Condole findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Condole condole);

    /**
     * 查询指定青年的慰问次数
     * @param poorTeenagersId 贫困青年ID
     * @return 慰问次数
     */
    int condoleCounts(String poorTeenagersId);
}
