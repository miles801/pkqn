package com.michael.spec.vo;

import com.ycrl.base.common.CommonVo;

import java.util.Date;

/**
 * @author Michael
 */
public class CondoleVo extends CommonVo {
    // 贫困青少年ID
    private String poorTeenagerId;
    // 贫困青少年名称
    private String poorTeenagerName;

    // 慰问主题
    private String title;
    private String titleName;

    private String members;
    // 慰问金额
    private Double money;

    // 发生时间
    private Date occurDate;
    private String orgId;
    private String orgName;

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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

    public String getPoorTeenagerId() {
        return poorTeenagerId;
    }

    public void setPoorTeenagerId(String poorTeenagerId) {
        this.poorTeenagerId = poorTeenagerId;
    }

    public String getPoorTeenagerName() {
        return poorTeenagerName;
    }

    public void setPoorTeenagerName(String poorTeenagerName) {
        this.poorTeenagerName = poorTeenagerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }
}
