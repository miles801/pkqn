package com.michael.spec.service;

import com.ycrl.core.pager.PageVo;
import com.michael.spec.bo.PoorTeenagersBo;
import com.michael.spec.domain.PoorTeenagers;
import com.michael.spec.vo.PoorTeenagersVo;

/**
 * @author Michael
 * 
 */
public interface PoorTeenagersService {

    /**
     * 保存
     */
    String save(PoorTeenagers poorTeenagers);

    /**
     * 更新
     */
    void update(PoorTeenagers poorTeenagers);

    /**
     * 分页查询
     */
    PageVo pageQuery(PoorTeenagersBo bo);

    /**
     * 根据ID查询对象的信息
     */
    PoorTeenagersVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
