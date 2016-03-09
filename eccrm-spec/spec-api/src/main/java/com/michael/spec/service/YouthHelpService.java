package com.michael.spec.service;

import com.michael.spec.bo.YouthHelpBo;
import com.michael.spec.domain.YouthHelp;
import com.michael.spec.vo.YouthHelpVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface YouthHelpService {

    /**
     * 保存
     */
    String save(YouthHelp youthHelp);

    /**
     * 更新
     */
    void update(YouthHelp youthHelp);

    /**
     * 分页查询
     */
    PageVo pageQuery(YouthHelpBo bo);

    /**
     * 根据ID查询对象的信息
     */
    YouthHelpVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

}
