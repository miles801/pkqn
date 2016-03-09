package com.michael.spec.service;

import com.michael.spec.bo.YouthRelationBo;
import com.michael.spec.domain.YouthRelation;
import com.michael.spec.vo.YouthRelationVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface YouthRelationService {

    /**
     * 保存
     */
    String save(YouthRelation youthRelation);

    /**
     * 更新
     */
    void update(YouthRelation youthRelation);

    /**
     * 分页查询
     */
    PageVo pageQuery(YouthRelationBo bo);

    /**
     * 根据ID查询对象的信息
     */
    YouthRelationVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
