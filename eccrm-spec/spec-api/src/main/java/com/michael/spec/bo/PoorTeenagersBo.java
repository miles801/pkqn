package com.michael.spec.bo;

import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.LikeModel;
import com.ycrl.core.hibernate.criteria.MatchModel;

/**
 * @author Michael
 */
public class PoorTeenagersBo implements BO {

    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    private String name;

    @Condition
    private String orgId;

    @Condition
    private String phone;
    @Condition
    private String qq;

    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    private String school;

    @Condition
    private String sex;

    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    private String address;

    // 金额
    @Condition(matchMode = MatchModel.GE, target = "condoleMoney")
    private Double minMoney;
    @Condition(matchMode = MatchModel.LT, target = "condoleMoney")
    private Double maxMoney;
    @Condition
    private Integer condoleTimes;


    public Integer getCondoleTimes() {
        return condoleTimes;
    }

    public void setCondoleTimes(Integer condoleTimes) {
        this.condoleTimes = condoleTimes;
    }

    public Double getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(Double minMoney) {
        this.minMoney = minMoney;
    }

    public Double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Double maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
