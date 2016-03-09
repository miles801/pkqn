package com.michael.spec.dao;

import com.michael.spec.bo.VolunteerBo;
import com.michael.spec.domain.Volunteer;

import java.util.List;

/**
 * @author Michael
 */
public interface VolunteerDao {

    String save(Volunteer volunteer);

    void update(Volunteer volunteer);

    /**
     * 高级查询接口
     */
    List<Volunteer> query(VolunteerBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(VolunteerBo bo);

    Volunteer findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(Volunteer volunteer);
}
