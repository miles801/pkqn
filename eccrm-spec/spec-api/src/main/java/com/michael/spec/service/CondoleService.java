package com.michael.spec.service;

import com.ycrl.core.pager.PageVo;
import com.michael.spec.bo.CondoleBo;
import com.michael.spec.domain.Condole;
import com.michael.spec.vo.CondoleVo;

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

}
