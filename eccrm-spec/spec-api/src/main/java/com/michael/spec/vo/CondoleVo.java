package com.michael.spec.vo;

import com.ycrl.base.common.CommonVo;

import java.util.Date;

/**
 * @author Michael
 */
public class CondoleVo extends CommonVo {
    // 贫困青年ID
    private String poorTeenagerId;
    // 贫困青年名称
    private String poorTeenagerName;

    private String title;

    // 慰问金额
    private Double money;

    // 发生时间
    private Date occurDate;

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
