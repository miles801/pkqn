package com.michael.spec.domain;

import com.ycrl.base.common.CommonDomain;
import eccrm.base.attachment.AttachmentSymbol;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 慰问记录
 *
 * @author Michael
 */
public class Condole extends CommonDomain implements AttachmentSymbol {

    // 贫困青少年ID
    @NotNull
    private String poorTeenagerId;
    // 贫困青少年名称
    @NotNull
    private String poorTeenagerName;

    // 冗余，报表需要
    private String orgId;
    private String orgName;

    @NotNull
    @Length(max = 40)
    private String title;

    // 参与人
    @Length(max = 40)
    private String members;

    // 慰问金额
    @NotNull
    private Double money;

    // 发生时间
    @NotNull
    private Date occurDate;


    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
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

    @Override
    public String businessId() {
        return getId();
    }
}
