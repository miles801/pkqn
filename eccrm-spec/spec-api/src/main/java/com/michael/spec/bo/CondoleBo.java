package com.michael.spec.bo;

import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.MatchModel;

import java.util.Date;

/**
 * @author Michael
 */
public class CondoleBo implements BO {

    @Condition
    private String poorTeenagerId;

    @Condition(matchMode = MatchModel.GE, target = "occurDate")
    private Date startDate;

    @Condition(matchMode = MatchModel.LT, target = "occurDate")
    private Date endDate;

    public String getPoorTeenagerId() {
        return poorTeenagerId;
    }

    public void setPoorTeenagerId(String poorTeenagerId) {
        this.poorTeenagerId = poorTeenagerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
