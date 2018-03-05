package com.springboot.racemanage.po;

public class Extend {

    private Integer id;
    private String uuid;
    private String theKey;
    private String theValue;
    private String forwho;
    private Integer status;

    @Override
    public String toString() {
        return "Extend{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", theKey='" + theKey + '\'' +
                ", theValue='" + theValue + '\'' +
                ", forwho='" + forwho + '\'' +
                ", status=" + status +
                '}';
    }

    public String getTheKey() {
        return theKey;
    }

    public void setTheKey(String theKey) {
        this.theKey = theKey;
    }

    public String getTheValue() {
        return theValue;
    }

    public void setTheValue(String theValue) {
        this.theValue = theValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getForwho() {
        return forwho;
    }

    public void setForwho(String forwho) {
        this.forwho = forwho;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
