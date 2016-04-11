package eccrm.base.employee.vo;


import com.ycrl.base.common.CommonVo;

import java.util.Date;

/**
 * Generated by yanhx on 2014-10-13.
 */

public class EmployeeVo extends CommonVo {

    private String employeeCode;
    private String employeeName;
    private String gender;
    private String genderName;
    private Date birthday;
    private String contry;
    private String provience;
    private String mobile;
    private String email;
    private String extensionNumber;
    private String city;
    private String region;
    private String address;
    private String idType;
    private String idNo;
    private String nation;
    private String nationName;
    private String zzmm;
    private String zzmmName;
    private String marriage;
    private String xueli;
    private String xueliName;
    private String xuewei;
    private String school;
    private String major;
    private Date beginWorkDate;
    private String workType;
    private String workTypeName;
    private String duty;
    private String dutyName;
    private String orgId;
    private String orgName;
    private String employeeId;
    private String statusName;
    private Boolean isBlank;//是否为白名单
    private String busiTypeName;
    private String tel;
    private String qq;

    private String positionId;
    private String positionName;
    private String positionCode;
    private String picture;
    // 公司
    private String company;

    private String tzz; //   团组织
    private String tzzName; //   团组织名称
    private String ly;  // 领域
    private String lyName;
    private String ly2;  // 领域
    private String ly2Name;
    private Integer zztgbCounts;    // 专职团干部数量
    private Integer jztgbCounts;    // 兼职团干部数量
    private String honor;           // 荣誉称号
    private String honorName;
    private Boolean isWorking;     // 是否在本县区从业
    private String isWorkingName;
    private Integer age;

    public String getIsWorkingName() {
        return isWorkingName;
    }

    public void setIsWorkingName(String isWorkingName) {
        this.isWorkingName = isWorkingName;
    }

    public String getXueliName() {
        return xueliName;
    }

    public void setXueliName(String xueliName) {
        this.xueliName = xueliName;
    }

    public String getLy2Name() {
        return ly2Name;
    }

    public void setLy2Name(String ly2Name) {
        this.ly2Name = ly2Name;
    }

    public String getLy2() {
        return ly2;
    }

    public void setLy2(String ly2) {
        this.ly2 = ly2;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLyName() {
        return lyName;
    }

    public void setLyName(String lyName) {
        this.lyName = lyName;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getZzmmName() {
        return zzmmName;
    }

    public void setZzmmName(String zzmmName) {
        this.zzmmName = zzmmName;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }

    public Integer getZztgbCounts() {
        return zztgbCounts;
    }

    public void setZztgbCounts(Integer zztgbCounts) {
        this.zztgbCounts = zztgbCounts;
    }

    public Integer getJztgbCounts() {
        return jztgbCounts;
    }

    public void setJztgbCounts(Integer jztgbCounts) {
        this.jztgbCounts = jztgbCounts;
    }

    public String getTzz() {
        return tzz;
    }

    public void setTzz(String tzz) {
        this.tzz = tzz;
    }

    public String getTzzName() {
        return tzzName;
    }

    public void setTzzName(String tzzName) {
        this.tzzName = tzzName;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getBusiTypeName() {
        return busiTypeName;
    }

    public void setBusiTypeName(String busiTypeName) {
        this.busiTypeName = busiTypeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
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

    public Boolean getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(Boolean isBlank) {
        this.isBlank = isBlank;
    }
}
