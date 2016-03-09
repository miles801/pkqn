package com.michael.spec.service;

import com.michael.spec.bo.YouthBo;
import com.michael.spec.domain.Youth;
import com.michael.spec.vo.YouthVo;
import com.ycrl.core.pager.PageVo;

import java.util.List;

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


    /**
     * 帮扶成功（状态改为“帮扶成功—待审核”）
     * 之前的状态只能是“已配对”
     *
     * @param youthId 青年ID
     */
    void confirmSuccess(String youthId);

    /**
     * 审核”帮扶成功-待审核“的青年
     *
     * @param youthId
     */
    void success(String youthId);

    /**
     * 解除帮扶（状态改为“接触帮扶-待审核”）
     *
     * @param youthId 青年ID
     */
    void confirmFail(String youthId);

    /**
     * 审核”帮扶成功-待审核“的青年
     *
     * @param youthId
     */
    void fail(String youthId);

    /**
     * 退回到“已配对”
     *
     * @param youthId 青年ID
     * @param reason  原因
     */
    void back(String youthId, String reason);

    /**
     * 统计各区县各状态青少年总数
     * [
     * 0:区县ID
     * 1：区县名称
     * 2：总记录数
     * 3：未配对
     * 4：正在帮扶
     * 5：帮扶成功
     * 6：解除帮扶
     * ]
     */
    List<Object[]> analysis();
}
