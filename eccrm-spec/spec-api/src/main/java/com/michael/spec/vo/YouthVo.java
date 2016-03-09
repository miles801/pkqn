package com.michael.spec.vo;

import com.michael.spec.domain.YouthRelation;
import com.ycrl.base.common.CommonVo;

import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
public class YouthVo extends CommonVo {
    private String name;
    private String sex;
    private String sexName;
    private String education;
    private String educationName;
    private String nation;
    private String nationName;
    private Date birthday;
    private Integer age;
    private String mobile;
    private String tel;
    private String qq;
    private String email;
    // 地址
    private String address;

    // 负责人
    private String ownerId;
    private String ownerName;
    // 所属县区
    private String orgId;
    private String orgName;

    private String state;
    private String stateName;

    // 头像
    private String picture;
    private String content;

    // 帮扶次数
    private Integer helpTimes;
    // 最后一次帮扶时间
    private Date lastHelpDate;

    private String reason;

    // 家庭关系
    private List<YouthRelation> relations;

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

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
