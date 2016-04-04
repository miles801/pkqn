package eccrm.base.employee.service.impl;

import com.michael.poi.annotation.Col;
import com.michael.poi.annotation.FieldConvert;
import com.michael.poi.annotation.ImportConfig;
import com.michael.poi.core.DTO;

import java.util.Date;

/**
 * @author Michael
 */
@ImportConfig(
        file = "",
        startRow = 2
)
public class EmployeeDTO implements DTO {
    @Col(index = 0)
    private String employeeName;
    @Col(index = 1)
    private String idNo;
    @Col(index = 2)
    private String orgName;
    @Col(index = 3)
    private String nation;
    @Col(index = 4)
    private String zzmm;
    @Col(index = 5)
    private Date beginWorkDate;
    @Col(index = 6)
    private String xueli;
    @Col(index = 7)
    private String ly;
    @Col(index = 8)
    @FieldConvert(convertorClass = BooleanConverter.class)
    private Boolean isWorking;     // 是否在本县区从业
    @Col(index = 9)
    private String honor;
    @Col(index = 10)
    private String mobile;
    @Col(index = 11)
    private String email;
    @Col(index = 12)
    private String description;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }


    public Date getBeginWorkDate() {
        return beginWorkDate;
    }

    public void setBeginWorkDate(Date beginWorkDate) {
        this.beginWorkDate = beginWorkDate;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
