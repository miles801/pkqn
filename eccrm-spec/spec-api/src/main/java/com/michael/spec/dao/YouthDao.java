package com.michael.spec.dao;

import com.michael.spec.bo.YouthBo;
import com.michael.spec.domain.Youth;

import java.util.List;

/**
 * @author Michael
 */
public interface YouthDao {

    String save(Youth youth);

    void update(Youth youth);

    /**
     * 高级查询接口
     */
    List<Youth> query(YouthBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(YouthBo bo);

    Youth findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Youth youth);

    /**
     * 统计各区县各状态青少年总数
     * [
     * 0:区县ID
     * 1：区县名称
     * 2：总记录数
     * 3：未配对
     * 4：正在帮扶
     * 5：帮扶成功
     * 6：解除帮扶
     * ]
     */
    List<Object[]> analysis();
}
