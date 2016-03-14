package com.michael.spec.domain;

import com.ycrl.base.common.CommonDomain;
import eccrm.base.attachment.AttachmentSymbol;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 闲散青少年
 *
 * @author Michael
 */
public class Youth extends CommonDomain implements AttachmentSymbol {

    public static final String STATE_NO_MATCH = "RED";
    public static final String STATE_MATCHED = "YELLOW";
    public static final String STATE_SUCCESS = "BLUE";
    public static final String STATE_SUCCESS_WAIT = "BLUE_WAIT";
    public static final String STATE_FAIL = "GRAY";
    public static final String STATE_FAIL_WAIT = "GRAY_WAIT";

    @NotNull(message = "姓名不能为空!")
    private String name;
    private String sex;
    private String education;
    private String nation;
    private Date birthday;
    private Integer age;
    private String mobile;
    // 改为身份证号码了
    private String tel;
    private String qq;
    private String email;
    // 地址
    private String address;

    // 负责人
    private String ownerId;
    private String ownerName;
    // 所属县区
    @NotNull(message = "所属区县不能为空!")
    private String orgId;
    private String orgName;

    @NotNull(message = "状态不能为空!")
    private String state;
    // 基本情况
    private String content;

    // 头像
    private String picture;

    // 帮扶次数
    private Integer helpTimes;
    // 最后一次帮扶时间
    private Date lastHelpDate;

    // 打回原因
    private String reason;
    // 兴趣爱好
    private String interest;

    // 家庭关系
    @Transient
    private List<YouthRelation> relations;


    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<YouthRelation> getRelations() {
        return relations;
    }

    public void setRelations(List<YouthRelation> relations) {
        this.relations = relations;
    }

    public Integer getHelpTimes() {
        return helpTimes;
    }

    public void setHelpTimes(Integer helpTimes) {
        this.helpTimes = helpTimes;
    }

    public Date getLastHelpDate() {
        return lastHelpDate;
    }

    public void setLastHelpDate(Date lastHelpDate) {
        this.lastHelpDate = lastHelpDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String businessId() {
        return getId();
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
