package com.springboot.racemanage.po;

import java.util.Date;

public class Task {
    private Integer id;
    private String uuid;
    private String title;
    private String description;
    private Date starttime;
    private Date endtime;
    private String fromUuid; // 组长或教师的uuid
    private String toUuid; //teamer 的uuid
    private String proUuid;
    private String file1;
    private Integer status;
    private Integer progress;
    private String solver;//解决者的名字

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", fromUuid='" + fromUuid + '\'' +
                ", toUuid='" + toUuid + '\'' +
                ", proUuid='" + proUuid + '\'' +
                ", file1='" + file1 + '\'' +
                ", status=" + status +
                ", progress=" + progress +
                ", solver='" + solver + '\'' +
                '}';
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
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

    public String getProUuid() {
        return proUuid;
    }

    public void setProUuid(String proUuid) {
        this.proUuid = proUuid;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }


    public String getProuuid() {
        return proUuid;
    }

    public void setProuuid(String proUuid) {
        this.proUuid = proUuid;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
