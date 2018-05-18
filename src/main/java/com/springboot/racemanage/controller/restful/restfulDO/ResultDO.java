package com.springboot.racemanage.controller.restful.restfulDO;

public class ResultDO {


    private Object result;//请求结果对象
    private int code;//请求状态吗码
    private String msg;//状态结果信息

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
