package com.baidu.springbootstarter.common;

import lombok.Data;

@Data
public class CommonException extends RuntimeException {

    private Result result;

    public CommonException(int code, String msg) {
        this(new Result(code, msg));
    }

    public CommonException(Result result) {
        super(result.getMsg());
        this.result = result;
    }
    public CommonException(int code, String msg, Throwable cause) {
        this(new Result(code, msg), cause);
    }

    public CommonException(Result result, Throwable cause) {
        super(result.getMsg(), cause);
        this.result = result;
    }
}
