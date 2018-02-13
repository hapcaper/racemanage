package com.springboot.racemanage.po;

public class Term {
    private Integer id;
    private String uuid;
    private Integer term;
    private Integer status;

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", term=" + term +
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

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
