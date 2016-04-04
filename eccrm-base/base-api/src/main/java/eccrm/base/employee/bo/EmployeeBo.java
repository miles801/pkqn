package eccrm.base.employee.bo;

import com.ycrl.core.hibernate.criteria.BO;
import eccrm.base.employee.domain.Employee;

import java.util.Date;

public class EmployeeBo extends Employee implements BO {

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

    public String getNotPosition() {
        return notPosition;
    }

    public void setNotPosition(String notPosition) {
        this.notPosition = notPosition;
    }

    public Boolean getHasPosition() {
        return hasPosition;
    }

    public void setHasPosition(Boolean hasPosition) {
        this.hasPosition = hasPosition;
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
}
