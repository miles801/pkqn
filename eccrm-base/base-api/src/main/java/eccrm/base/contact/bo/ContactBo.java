package eccrm.base.contact.bo;


import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.MatchModel;

import java.util.Date;

/**
 * Generated by chenl on 2014-09-22.
 */

public class ContactBo implements BO {
    @Condition
    private String resourceType;    // 客户类型*
    @Condition
    private String resourceId;      // 客户ID*
    @Condition(matchMode = MatchModel.LIKE)
    private String resourceName;    // 客户姓名
    @Condition
    private String code;            // 编号
    @Condition
    private String contactID;       // 联络方式（例如：email，微信，短信，电话）*
    @Condition(matchMode = MatchModel.LIKE)
    private String address;         // 地址或内容*

    private Date startDatetime;     // 开始时间*
    private Date endDatetime;       // 结束时间
    @Condition
    private String contactItem;     // 联络事项1
    @Condition
    private String contactItem2;    // 联络事项2
    @Condition
    private String contactItem3;    // 联络事项3
    @Condition
    private String niceId;          // 录音ID
    @Condition
    private String contactDirect;   // 联络方向*
    @Condition
    private String contactMethod;   // 联络方式
    @Condition
    private String resultType;      // 结果类型

    @Condition(matchMode = MatchModel.GE, target = "createdDatetime")
    private Date startDate;
    @Condition(matchMode = MatchModel.LE, target = "createdDatetime")
    private Date endDate;
    private Boolean board;

    public Boolean getBoard() {
        return board;
    }

    public void setBoard(Boolean board) {
        this.board = board;
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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getContactItem() {
        return contactItem;
    }

    public void setContactItem(String contactItem) {
        this.contactItem = contactItem;
    }

    public String getContactItem2() {
        return contactItem2;
    }

    public void setContactItem2(String contactItem2) {
        this.contactItem2 = contactItem2;
    }

    public String getContactItem3() {
        return contactItem3;
    }

    public void setContactItem3(String contactItem3) {
        this.contactItem3 = contactItem3;
    }

    public String getNiceId() {
        return niceId;
    }

    public void setNiceId(String niceId) {
        this.niceId = niceId;
    }

    public String getContactDirect() {
        return contactDirect;
    }

    public void setContactDirect(String contactDirect) {
        this.contactDirect = contactDirect;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
