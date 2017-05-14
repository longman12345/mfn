package com.mfn.common;

/**
 * Created by LPF on 2017/2/25.
 */
public class BaseMessage {
    private String code = "0";
    private String msg = "请求成功";

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

    @Override
    public String toString() {
        return "BaseMessage{" +
            "code='" + code + '\'' +
            ", msg='" + msg + '\'' +
            '}';
    }
}
