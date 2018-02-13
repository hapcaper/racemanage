package com.springboot.racemanage.po;

import java.util.Date;

public class Log {
    private Integer id;
    private String uuid;
    private String tasktitle;
    private String proUuid;
    private String teamerUuid;
    private Date starttime; //也许用不到
    private Date endtime; //也许用不到  这俩用于记录任务的详细开始和完成时间有点鸡肋用不到就让他空着
    private String description;
    private String taskUuid;
    private Integer status;
    private Date time;  //log的产生时间
    private String action;

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", taskname='" + tasktitle + '\'' +
                ", proUuid='" + proUuid + '\'' +
                ", teamerUuid='" + teamerUuid + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", description='" + description + '\'' +
                ", taskUuid='" + taskUuid + '\'' +
                ", status=" + status +
                ", time=" + time +
                ", action='" + action + '\'' +
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

    public String getTaskname() {
        return tasktitle;
    }

    public void setTaskname(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getProUuid() {
        return proUuid;
    }

    public void setProUuid(String proUuid) {
        this.proUuid = proUuid;
    }

    public String getTeamerUuid() {
        return teamerUuid;
    }

    public void setTeamerUuid(String teamerUuid) {
        this.teamerUuid = teamerUuid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskUuid() {
        return taskUuid;
    }

    public void setTaskUuid(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
