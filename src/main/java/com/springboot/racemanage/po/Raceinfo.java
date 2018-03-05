package com.springboot.racemanage.po;

import java.util.Date;

public class Raceinfo {
    private Integer id;
    private String uuid;
    private String racename;
    private String description;
    private String kind;
    private Date begaintime;
    private Date endtime;
    private Integer status;
    private Integer term;
    private String file1;

    @Override
    public String toString() {
        return "Raceinfo{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", racename='" + racename + '\'' +
                ", description='" + description + '\'' +
                ", kind='" + kind + '\'' +
                ", begaintime=" + begaintime +
                ", endtime=" + endtime +
                ", status=" + status +
                ", term=" + term +
                ", file1='" + file1 + '\'' +
                '}';
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
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

    public String getRacename() {
        return racename;
    }

    public void setRacename(String racename) {
        this.racename = racename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Date getBegaintime() {
        return begaintime;
    }

    public void setBegaintime(Date begaintime) {
        this.begaintime = begaintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

}
