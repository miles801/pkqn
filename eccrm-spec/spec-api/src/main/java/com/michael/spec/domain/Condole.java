package com.michael.spec.domain;

import com.ycrl.base.common.CommonDomain;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 慰问记录
 *
 * @author Michael
 */
public class Condole extends CommonDomain {

    // 贫困青年ID
    @NotNull
    private String poorTeenagerId;
    // 贫困青年名称
    @NotNull
    private String poorTeenagerName;

    @NotNull
    @Length(max = 50)
    private String title;

    // 慰问金额
    @NotNull
    private Double money;

    // 发生时间
    @NotNull
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
