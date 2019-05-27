package com.baidu.springbootstarter.common;

public class CommonController {

    protected <T> Result<T>  sendSuccess(T data) {
        return new Result<>(Result.SUCCESS, data);
    }

    protected <T> Result<T>  sendFailure(T data) {
        return new Result<>(Result.FAILURE, data);
    }
    protected <T> Result<T>  sendFailure(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

}
