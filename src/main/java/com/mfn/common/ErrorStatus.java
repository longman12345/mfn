package com.mfn.common;

/**
 * Created by LPF on 2017/4/8.
 */
public enum ErrorStatus {
    SYSTEM_ERROR("1000", "系统异常"),
    PARAMETER_ERROR("1001", "参数不合法");

    private String code;
    private String msg;

    private ErrorStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
