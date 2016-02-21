package eccrm.base.employee.domain;

import com.ycrl.base.common.CommonDomain;
import eccrm.base.org.domain.Organization;

import java.util.Date;

/**
 * Created by qy on 14-10-15.
 */

public class Employee extends CommonDomain {
    // 常量：职务
    public static final String PARAM_DUTY = "BP_ZHIW";
    private String employeeCode;
    private String employeeName;
    private String gender;
    private Date birthday;
    private String contry;
    private String provience;
    private String city;
    private String mobile;
    private String email;
    /**
     * 分机号（作为RTX账号使用）
     */
    private String extensionNumber;
    private String region;
    private String address;
    private String idType;
    private String idNo;
    private String nation;
    private String zzmm;
    private String marriage;
    private String xueli;
    private String xuewei;
    private String school;
    private String major;
    private Date beginWorkDate;
    private String workType;
    private String duty;
    private String orgId;
    private String positionId;
    private String wCountry;
    private String wProvience;
    private Organization organization;
    private String wCity;
    private String wRegion;
    private String wAddress;


    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
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

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public String getProvience() {
        return provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
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

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getXuewei() {
        return xuewei;
    }

    public void setXuewei(String xuewei) {
        this.xuewei = xuewei;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getBeginWorkDate() {
        return beginWorkDate;
    }

    public void setBeginWorkDate(Date beginWorkDate) {
        this.beginWorkDate = beginWorkDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getwCountry() {
        return wCountry;
    }

    public void setwCountry(String wCountry) {
        this.wCountry = wCountry;
    }

    public String getwProvience() {
        return wProvience;
    }

    public void setwProvience(String wProvience) {
        this.wProvience = wProvience;
    }

    public String getwCity() {
        return wCity;
    }

    public void setwCity(String wCity) {
        this.wCity = wCity;
    }

    public String getwRegion() {
        return wRegion;
    }

    public void setwRegion(String wRegion) {
        this.wRegion = wRegion;
    }

    public String getwAddress() {
        return wAddress;
    }

    public void setwAddress(String wAddress) {
        this.wAddress = wAddress;
    }
}
