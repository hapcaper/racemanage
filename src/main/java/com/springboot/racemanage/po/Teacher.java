package com.springboot.racemanage.po;

public class Teacher {

    private Integer id;
    private String tUuid;
    private String tName;
    private String tEmail;
    private String tPhoto;
    private String tPassword;
    private String tPhone;
    private String tNumber;
    private Integer tStatus;
    private String tDuty;
    private String tDescription;
    private String tGender;



    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tUuid='" + tUuid + '\'' +
                ", tName='" + tName + '\'' +
                ", tEmail='" + tEmail + '\'' +
                ", tPhoto='" + tPhoto + '\'' +
                ", tPassword='" + tPassword + '\'' +
                ", tPhone='" + tPhone + '\'' +
                ", tNumber='" + tNumber + '\'' +
                ", tStatus=" + tStatus +
                ", tDuty='" + tDuty + '\'' +
                ", tDescription='" + tDescription + '\'' +
                ", tGender='" + tGender + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettUuid() {
        return tUuid;
    }

    public void settUuid(String tUuid) {
        this.tUuid = tUuid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String gettPhoto() {
        return tPhoto;
    }

    public void settPhoto(String tPhoto) {
        this.tPhoto = tPhoto;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettNumber() {
        return tNumber;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public String gettDuty() {
        return tDuty;
    }

    public void settDuty(String tDuty) {
        this.tDuty = tDuty;
    }

    public String gettDescription() {
        return tDescription;
    }

    public void settDescription(String tDescription) {
        this.tDescription = tDescription;
    }

    public String gettGender() {
        return tGender;
    }

    public void settGender(String tGender) {
        this.tGender = tGender;
    }
}
