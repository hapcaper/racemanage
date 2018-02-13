package com.springboot.racemanage.po;

public class Leader {

    private Integer id;
    private String lUuid;
    private String lName;
    private String lEmail;
    private String lPhoto;
    private String lPassword;
    private String lPhone;
    private String lNumber;
    private Integer lStatus;
    private String lDuty;
    private String lDescription;
    private String lGender;



    @Override
    public String toString() {
        return "Leader{" +
                "id=" + id +
                ", lUuid='" + lUuid + '\'' +
                ", lName='" + lName + '\'' +
                ", lEmail='" + lEmail + '\'' +
                ", lPhoto='" + lPhoto + '\'' +
                ", lPassword='" + lPassword + '\'' +
                ", lPhone='" + lPhone + '\'' +
                ", lNumber='" + lNumber + '\'' +
                ", lStatus=" + lStatus +
                ", lDuty='" + lDuty + '\'' +
                ", lDescription='" + lDescription + '\'' +
                ", lGender='" + lGender + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getlUuid() {
        return lUuid;
    }

    public void setlUuid(String lUuid) {
        this.lUuid = lUuid;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlEmail() {
        return lEmail;
    }

    public void setlEmail(String lEmail) {
        this.lEmail = lEmail;
    }

    public String getlPhoto() {
        return lPhoto;
    }

    public void setlPhoto(String lPhoto) {
        this.lPhoto = lPhoto;
    }

    public String getlPassword() {
        return lPassword;
    }

    public void setlPassword(String lPassword) {
        this.lPassword = lPassword;
    }

    public String getlPhone() {
        return lPhone;
    }

    public void setlPhone(String lPhone) {
        this.lPhone = lPhone;
    }

    public String getlNumber() {
        return lNumber;
    }

    public void setlNumber(String lNumber) {
        this.lNumber = lNumber;
    }

    public Integer getlStatus() {
        return lStatus;
    }

    public void setlStatus(Integer lStatus) {
        this.lStatus = lStatus;
    }

    public String getlDuty() {
        return lDuty;
    }

    public void setlDuty(String lDuty) {
        this.lDuty = lDuty;
    }

    public String getlDescription() {
        return lDescription;
    }

    public void setlDescription(String lDescription) {
        this.lDescription = lDescription;
    }

    public String getlGender() {
        return lGender;
    }

    public void setlGender(String lGender) {
        this.lGender = lGender;
    }
}
