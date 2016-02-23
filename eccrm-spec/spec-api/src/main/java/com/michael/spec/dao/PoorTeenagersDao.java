package com.michael.spec.dao;

import com.michael.spec.bo.PoorTeenagersBo;
import com.michael.spec.domain.PoorTeenagers;

import java.util.List;

/**
 * @author Michael
 */
public interface PoorTeenagersDao {

    String save(PoorTeenagers poorTeenagers);

    void update(PoorTeenagers poorTeenagers);

    /**
     * 高级查询接口
     */
    List<PoorTeenagers> query(PoorTeenagersBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(PoorTeenagersBo bo);

    PoorTeenagers findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(PoorTeenagers poorTeenagers);

    /**
     * 查询指定年的贫困青少年信息
     *
     * @param year 年
     * @return [机构ID，机构名称，个数]
     */
    List<Object[]> analysisTeenagers(int year);
}
