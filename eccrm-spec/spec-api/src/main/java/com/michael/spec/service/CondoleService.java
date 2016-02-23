package com.michael.spec.service;

import com.michael.spec.bo.CondoleBo;
import com.michael.spec.domain.Condole;
import com.michael.spec.vo.CondoleVo;
import com.ycrl.core.pager.PageVo;

import java.util.List;

/**
 * @author Michael
 * 
 */
public interface CondoleService {

    /**
     * 保存
     */
    String save(Condole condole);

    /**
     * 更新
     */
    void update(Condole condole);

    /**
     * 分页查询
     */
    PageVo pageQuery(CondoleBo bo);

    /**
     * 根据ID查询对象的信息
     */
    CondoleVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

    /**
     * 根据青少年ID查询其对应的慰问记录
     *
     * @param teenagerId 青少年ID
     * @return 慰问记录
     */
    List<CondoleVo> queryByTeenager(String teenagerId);

    /**
     * 统计指定年限的慰问次数
     *
     * @param year 年
     * @return [机构ID, 机构名称, 慰问次数, 慰问金额]
     */
    List<Object[]> analysisCondole(int year);
}
