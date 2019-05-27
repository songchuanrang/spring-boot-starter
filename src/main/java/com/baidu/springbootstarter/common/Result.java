package com.baidu.springbootstarter.common;

import lombok.Data;

@Data
public class Result<T> {

    public static final int SUCCESS_CODE = 1000;
    public static final int FAILURE_CODE = 0;
    public static final Result SUCCESS = new Result(SUCCESS_CODE, "请求成功");
    public static final Result FAILURE = new Result(FAILURE_CODE, "请求失败");

    private int code;
    private String msg;
    private T data;

    Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    Result(Result result, T data) {
        this(result.getCode(), result.getMsg(), data);
    }

    Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
