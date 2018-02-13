package com.springboot.racemanage.po;

public class Project {

    private Integer id;
    private String uuid;
    private String name;
    private Integer status;
    private String description;
    private String document;
    private String persons;
    private Integer progress;
    private String headman;
    private String tUuid;
    private String tname;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", document='" + document + '\'' +
                ", persons='" + persons + '\'' +
                ", progress=" + progress +
                ", headman='" + headman + '\'' +
                ", tUuid='" + tUuid + '\'' +
                ", tname='" + tname + '\'' +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getHeadman() {
        return headman;
    }

    public void setHeadman(String headman) {
        this.headman = headman;
    }

    public String gettUuid() {
        return tUuid;
    }

    public void settUuid(String tUuid) {
        this.tUuid = tUuid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
