package com.michael.spec.service;

import com.michael.spec.bo.YouthBo;
import com.michael.spec.domain.Youth;
import com.michael.spec.vo.YouthVo;
import com.ycrl.core.pager.PageVo;

/**
 * @author Michael
 */
public interface YouthService {

    /**
     * 保存
     */
    String save(Youth youth);

    /**
     * 更新
     */
    void update(Youth youth);

    /**
     * 分页查询
     */
    PageVo pageQuery(YouthBo bo);

    /**
     * 根据ID查询对象的信息
     */
    YouthVo findById(String id);

    /**
     * 批量删除
     */
    void deleteByIds(String[] ids);

    /**
     * 配对负责人
     *
     * @param youthId   闲散青年ID
     * @param ownerId   负责人ID
     * @param ownerName 负责人名称
     */
    void match(String youthId, String ownerId, String ownerName);

    /**
     * 查询指定的负责人是否已经有配对的闲散青年
     *
     * @param ownerId 负责人ID
     * @return true：有，false：没有
     */
    boolean hasMatched(String ownerId);

    /**
     * 取消配对
     *
     * @param id 青年ID
     */
    void clearOwner(String id);
}
