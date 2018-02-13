package com.springboot.racemanage.po;

import java.util.Date;

public class Invite {
    private Integer id;
    private String uuid;
    private String from;
    private String to;
    private Date sendtime;
    private Integer status;
    private String proUuid;
    private String proname;
    private String teamerDescription;
    private String duty;
    private String dutydescription;

    @Override
    public String toString() {
        return "Invite{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", sendtime=" + sendtime +
                ", status=" + status +
                ", proUuid='" + proUuid + '\'' +
                ", proname='" + proname + '\'' +
                ", teamerDescription='" + teamerDescription + '\'' +
                ", duty='" + duty + '\'' +
                ", dutydescription='" + dutydescription + '\'' +
                '}';
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProUuid() {
        return proUuid;
    }

    public void setProUuid(String proUuid) {
        this.proUuid = proUuid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getTeamerDescription() {
        return teamerDescription;
    }

    public void setTeamerDescription(String teamerDescription) {
        this.teamerDescription = teamerDescription;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDutydescription() {
        return dutydescription;
    }

    public void setDutydescription(String dutydescription) {
        this.dutydescription = dutydescription;
    }
}
