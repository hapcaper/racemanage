package com.springboot.racemanage.po;

public class Teamer {
    private Integer id;
    private String uuid;
    private String proUuid;
    private String strUuid;
    private String description;
    private String duty;
    private String stuname;
    private Integer progress;
    private String dutydescription;
    private String proame;
    private Integer status;

    @Override
    public String toString() {
        return "Teamer{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", proUuid='" + proUuid + '\'' +
                ", strUuid='" + strUuid + '\'' +
                ", description='" + description + '\'' +
                ", duty='" + duty + '\'' +
                ", stuname='" + stuname + '\'' +
                ", progress=" + progress +
                ", dutydescription='" + dutydescription + '\'' +
                ", proame='" + proame + '\'' +
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

    public String getProUuid() {
        return proUuid;
    }

    public void setProUuid(String proUuid) {
        this.proUuid = proUuid;
    }

    public String getStrUuid() {
        return strUuid;
    }

    public void setStrUuid(String strUuid) {
        this.strUuid = strUuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getDutydescription() {
        return dutydescription;
    }

    public void setDutydescription(String dutydescription) {
        this.dutydescription = dutydescription;
    }

    public String getProame() {
        return proame;
    }

    public void setProame(String proame) {
        this.proame = proame;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
