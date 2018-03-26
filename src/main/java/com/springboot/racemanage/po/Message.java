package com.springboot.racemanage.po;

import java.util.Date;

public class Message {

    private Integer id;
    private String uuid;
    private String content;
    private String fromUuid;
    private String toUuid;
    private Date sendtime;
    private String title;
    private Integer status;
    private String stuOrTe;



    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", content='" + content + '\'' +
                ", fromUuid='" + fromUuid + '\'' +
                ", toUuid='" + toUuid + '\'' +
                ", sendtime=" + sendtime +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", stuOrTe='" + stuOrTe + '\'' +
                '}';
    }

    public String getStuOrTe() {
        return stuOrTe;
    }

    public void setStuOrTe(String stuOrTe) {
        this.stuOrTe = stuOrTe;
    }

    public String getFromUuid() {
        return fromUuid;
    }

    public void setFromUuid(String fromUuid) {
        this.fromUuid = fromUuid;
    }

    public String getToUuid() {
        return toUuid;
    }

    public void setToUuid(String toUuid) {
        this.toUuid = toUuid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
