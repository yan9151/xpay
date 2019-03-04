package com.xpay.admin.console.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Paging<T> {
    @JsonProperty(value = "data")
    private List<T> list;
    private Long count = 0L;
    private String msg;
    private int code = 0;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
