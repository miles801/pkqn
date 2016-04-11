package eccrm.base.employee.bo;

import com.ycrl.core.hibernate.criteria.BO;
import com.ycrl.core.hibernate.criteria.Condition;
import com.ycrl.core.hibernate.criteria.LikeModel;
import com.ycrl.core.hibernate.criteria.MatchModel;

import java.util.Date;

public class EmployeeBo implements BO {

    /**
     * 如果该值为true，表示查询状态有启用，且处于在职的员工（正式、实习、调动中）
     */
    private Boolean valid;

    /**
     * 启用数据权限过滤
     */
    private Boolean permission;

    /**
     * 是否拥有直属岗位
     */
    private Boolean hasPosition;

    private String notPosition;

    private Date birthday1;
    private Date birthday2;

    @Condition
    private String orgId;

    @Condition
    private String positionCode;

    @Condition(matchMode = MatchModel.LIKE, likeMode = LikeModel.ANYWHERE)
    private String employeeName;

    @Condition
    private String employeeCode;
    @Condition
    private String mobile;

    @Condition
    private String duty;
    @Condition
    private String statue;

    @Condition(matchMode = MatchModel.GE, target = "age")
    private Integer age1;
    @Condition(matchMode = MatchModel.LE, target = "age")
    private Integer age2;

    @Condition
    private String ly;
    @Condition
    private String ly2;

    @Condition
    private String honor;
    private Integer year;

    @Condition
    private String tzz;
    @Condition
    private String tzzName;

    @Condition(matchMode = MatchModel.GE, target = "beginWorkDate")
    private Date beginWorkDate1;
    @Condition(matchMode = MatchModel.LT, target = "beginWorkDate")
    private Date beginWorkDate2;

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

    public Date getBeginWorkDate2() {
        return beginWorkDate2;
    }

    public void setBeginWorkDate2(Date beginWorkDate2) {
        this.beginWorkDate2 = beginWorkDate2;
    }

    public Date getBeginWorkDate1() {
        return beginWorkDate1;
    }

    public void setBeginWorkDate1(Date beginWorkDate1) {
        this.beginWorkDate1 = beginWorkDate1;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }

    public Boolean getHasPosition() {
        return hasPosition;
    }

    public void setHasPosition(Boolean hasPosition) {
        this.hasPosition = hasPosition;
    }

    public String getNotPosition() {
        return notPosition;
    }

    public void setNotPosition(String notPosition) {
        this.notPosition = notPosition;
    }

    public Date getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(Date birthday1) {
        this.birthday1 = birthday1;
    }

    public Date getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Date birthday2) {
        this.birthday2 = birthday2;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public Integer getAge1() {
        return age1;
    }

    public void setAge1(Integer age1) {
        this.age1 = age1;
    }

    public Integer getAge2() {
        return age2;
    }

    public void setAge2(Integer age2) {
        this.age2 = age2;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getLy2() {
        return ly2;
    }

    public void setLy2(String ly2) {
        this.ly2 = ly2;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }
}
