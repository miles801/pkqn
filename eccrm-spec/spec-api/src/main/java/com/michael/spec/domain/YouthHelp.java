package com.michael.spec.domain;

import com.ycrl.base.common.CommonDomain;
import eccrm.base.attachment.AttachmentSymbol;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 帮扶记录
 *
 * @author Michael
 */
public class YouthHelp extends CommonDomain implements AttachmentSymbol {

    @NotNull
    private String title;
    private String content;

    // 青年
    @NotNull
    private String youthId;
    private String youthName;
    // 发生时间
    @NotNull
    private Date occurDate;


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

    @Override
    public String businessId() {
        return getId();
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
}
