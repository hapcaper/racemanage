package com.springboot.racemanage.po;

public class Student {

    private Integer id;
    private String stuUuid;
    private String stuEmail;
    private String stuNumber;
    private String stuName;
    private String stuPhone;
    private String stuPassword;
    private String stuDescription;
    private String stuDuty;
    private Integer stuStatus;
    private String stuGender;
    private String photo;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuUuid='" + stuUuid + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuPassword='" + stuPassword + '\'' +
                ", stuDescription='" + stuDescription + '\'' +
                ", stuDuty='" + stuDuty + '\'' +
                ", stuStatus='" + stuStatus + '\'' +
                ", stuGender='" + stuGender + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuUuid() {
        return stuUuid;
    }

    public void setStuUuid(String stuUuid) {
        this.stuUuid = stuUuid;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuDescription() {
        return stuDescription;
    }

    public void setStuDescription(String stuDescription) {
        this.stuDescription = stuDescription;
    }

    public String getStuDuty() {
        return stuDuty;
    }

    public void setStuDuty(String stuDuty) {
        this.stuDuty = stuDuty;
    }

    public Integer getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(Integer stuStatus) {
        this.stuStatus = stuStatus;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
