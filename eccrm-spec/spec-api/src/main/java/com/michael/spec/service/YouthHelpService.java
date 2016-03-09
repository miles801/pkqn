package com.michael.spec.service;

import com.michael.spec.bo.YouthHelpBo;
import com.michael.spec.domain.YouthHelp;
import com.michael.spec.vo.YouthHelpVo;
import com.ycrl.core.pager.PageVo;

import java.util.List;

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

    /**
     * 统计指定年份，指定月份的工作进度
     *
     * @param year  年份
     * @param month 月份
     * @return [负责人名称、负责人职务、青年名称、性别、年龄、电话、帮扶标题、状态]
     */
    List<Object[]> workMonthReport(int year, int month);

}
