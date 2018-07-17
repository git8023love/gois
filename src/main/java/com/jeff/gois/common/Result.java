package com.jeff.gois.common;

public class Result<T> {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回内容
     */
    private T result;
    /**
     * 返回信息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                '}';
    }
}
