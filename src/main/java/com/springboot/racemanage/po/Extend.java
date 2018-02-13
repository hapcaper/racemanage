package com.springboot.racemanage.po;

public class Extend {

    private Integer id;
    private String uuid;
    private String key;
    private String value;
    private String forwho;
    private Integer status;

    @Override
    public String toString() {
        return "Extend{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", forwho='" + forwho + '\'' +
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
