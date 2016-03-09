package com.michael.spec.domain;

import com.ycrl.base.common.CommonDomain;

import javax.validation.constraints.NotNull;

/**
 * 志愿者
 *
 * @author Michael
 */
public class Volunteer extends CommonDomain {
    // 姓名
    @NotNull
    private String name;
    // 性别
    private String sex;
    // 身份证号码
    private String idCard;
    // 联系电话
    private String phone;
    // 专业
    private String duty;

    // 归属于哪个团干部
    @NotNull
    private String ownerId;
    private String ownerName;

    // 属于哪个县区
    private String orgId;
    private String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
