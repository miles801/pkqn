package com.michael.spec.bo;

import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.LikeModel;
import com.ycrl.core.hibernate.criteria.MatchModel;

import java.util.Date;

/**
 * @author Michael
 */
public class YouthHelpBo implements BO {

    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    private String title;

    @Condition
    private String youthId;
    // 发生时间
    @Condition(matchMode = MatchModel.GE, target = "occurDate")
    private Date occurDate1;
    @Condition(matchMode = MatchModel.LT, target = "occurDate")
    private Date occurDate2;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYouthId() {
        return youthId;
    }

    public void setYouthId(String youthId) {
        this.youthId = youthId;
    }

    public Date getOccurDate1() {
        return occurDate1;
    }

    public void setOccurDate1(Date occurDate1) {
        this.occurDate1 = occurDate1;
    }

    public Date getOccurDate2() {
        return occurDate2;
    }

    public void setOccurDate2(Date occurDate2) {
        this.occurDate2 = occurDate2;
    }
}
