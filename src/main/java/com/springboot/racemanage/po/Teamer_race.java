package com.springboot.racemanage.po;

public class Teamer_race {

    private Integer id;
    private String uuid;
    private String teamerUuid;
    private String raceUuid;
    private Integer status;



    @Override
    public String toString() {
        return "teamer_race{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", teamerUuid='" + teamerUuid + '\'' +
                ", raceUuid='" + raceUuid + '\'' +
                ", status=" + status +
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

    public String getTeamerUuid() {
        return teamerUuid;
    }

    public void setTeamerUuid(String teamerUuid) {
        this.teamerUuid = teamerUuid;
    }

    public String getRaceUuid() {
        return raceUuid;
    }

    public void setRaceUuid(String raceUuid) {
        this.raceUuid = raceUuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
