package com.michael.spec.dao;

import com.michael.spec.bo.YouthHelpBo;
import com.michael.spec.domain.YouthHelp;

import java.util.List;

/**
 * @author Michael
 */
public interface YouthHelpDao {

    String save(YouthHelp youthHelp);

    void update(YouthHelp youthHelp);

    /**
     * 高级查询接口
     */
    List<YouthHelp> query(YouthHelpBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(YouthHelpBo bo);

    YouthHelp findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(YouthHelp youthHelp);

    /**
     * 删除指定青年下的所有帮扶记录
     *
     * @param youthId 青年ID
     */
    void deleteByYouth(String youthId);


    /**
     * 统计指定年份，指定月份的工作进度
     *
     * @param year  年份
     * @param month 月份
     * @return [负责人名称、负责人职务、青年名称、性别、年龄、电话、帮扶标题、状态]
     */
    List<Object[]> workMonthReport(int year, int month);
}
