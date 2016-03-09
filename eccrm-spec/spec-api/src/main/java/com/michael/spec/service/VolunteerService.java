package com.michael.spec.service;

import com.michael.spec.bo.VolunteerBo;
import com.michael.spec.domain.Volunteer;
import com.michael.spec.vo.VolunteerVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface VolunteerService {

    /**
     * 保存
     */
    String save(Volunteer volunteer);

    /**
     * 更新
     */
    void update(Volunteer volunteer);

    /**
     * 分页查询
     */
    PageVo pageQuery(VolunteerBo bo);

    /**
     * 根据ID查询对象的信息
     */
    VolunteerVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
