package com.michael.spec.vo;

import com.ycrl.base.common.CommonVo;

import java.util.Date;

/**
 * @author Michael
 */
public class YouthHelpVo extends CommonVo {

    private String title;
    private String content;

    // 青年
    private String youthId;
    private String youthName;
    // 发生时间
    private Date occurDate;


    private String volunteerIds;
    private String volunteerNames;

    public String getVolunteerIds() {
        return volunteerIds;
    }

    public void setVolunteerIds(String volunteerIds) {
        this.volunteerIds = volunteerIds;
    }

    public String getVolunteerNames() {
        return volunteerNames;
    }

    public void setVolunteerNames(String volunteerNames) {
        this.volunteerNames = volunteerNames;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getYouthId() {
        return youthId;
    }

    public void setYouthId(String youthId) {
        this.youthId = youthId;
    }

    public String getYouthName() {
        return youthName;
    }

    public void setYouthName(String youthName) {
        this.youthName = youthName;
    }

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }
}
