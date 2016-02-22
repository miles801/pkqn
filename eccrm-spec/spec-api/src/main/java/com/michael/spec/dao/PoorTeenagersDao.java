package com.michael.spec.dao;

import com.michael.spec.bo.PoorTeenagersBo;
import com.michael.spec.domain.PoorTeenagers;
import com.michael.spec.vo.PoorTeenagersVo;
import java.util.List;

/**
 * @author Michael
 */
public interface PoorTeenagersDao {

    String save(PoorTeenagers poorTeenagers);

    void update(PoorTeenagers poorTeenagers);

    /**
     * 高级查询接口
     */
    List<PoorTeenagers> query(PoorTeenagersBo bo);

    /**
     * 查询总记录数
     */
    Long getTotal(PoorTeenagersBo bo);

    PoorTeenagers findById(String id);

    void deleteById(String id);

    /**
     * 根据实体对象删除
     * 必须保证该实体是存在的（一般是get或者load得到的对象）
     */
    void delete(PoorTeenagers poorTeenagers);
}
