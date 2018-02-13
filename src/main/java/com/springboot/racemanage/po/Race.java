package com.springboot.racemanage.po;

public class Race {
    private Integer id;
    private String uuid;
    private String racename;
    private String raceteacher;
    private Integer status;
    private String description;
    private String kind;
    private Integer progress;
    private Integer result;
    private String tUuid;
    private Integer term;
    private String proname;
    private String file1;
    private String file2;
    private String file3;

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", racename='" + racename + '\'' +
                ", raceteacher='" + raceteacher + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", kind='" + kind + '\'' +
                ", progress=" + progress +
                ", result=" + result +
                ", tUuid='" + tUuid + '\'' +
                ", term=" + term +
                ", proname='" + proname + '\'' +
                ", file1='" + file1 + '\'' +
                ", file2='" + file2 + '\'' +
                ", file3='" + file3 + '\'' +
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

    public String getRacename() {
        return racename;
    }

    public void setRacename(String racename) {
        this.racename = racename;
    }

    public String getRaceteacher() {
        return raceteacher;
    }

    public void setRaceteacher(String raceteacher) {
        this.raceteacher = raceteacher;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String gettUuid() {
        return tUuid;
    }

    public void settUuid(String tUuid) {
        this.tUuid = tUuid;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getFile3() {
        return file3;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }
}
