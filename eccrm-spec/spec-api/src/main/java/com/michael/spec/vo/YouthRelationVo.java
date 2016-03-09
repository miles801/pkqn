package com.michael.spec.vo;

import com.ycrl.base.common.CommonVo;

/**
 * @author Michael
 */
public class YouthRelationVo extends CommonVo {

    private String youthId;
    private String name;

    private String relation;

    private Integer age;
    private String education;
    private String duty;
    private String phone;

    public String getYouthId() {
        return youthId;
    }

    public void setYouthId(String youthId) {
        this.youthId = youthId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
