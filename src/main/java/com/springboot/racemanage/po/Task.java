package com.springboot.racemanage.po;

import java.util.Date;

public class Task {
    private Integer id;
    private String uuid;
    private String title;
    private String description;
    private Date starttime;
    private Date endtime;
    private String from; // 组长或教师的uuid
    private String to; //teamer 的uuid
    private String proUuid;
    private String file;
    private Integer status;
    private Integer progress;
    private String solver;

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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", proUuid='" + proUuid + '\'' +
                ", file='" + file + '\'' +
                ", status=" + status +
                ", progress=" + progress +
                ", solver='" + solver + '\'' +
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

    public String getProuuid() {
        return proUuid;
    }

    public void setProuuid(String proUuid) {
        this.proUuid = proUuid;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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
