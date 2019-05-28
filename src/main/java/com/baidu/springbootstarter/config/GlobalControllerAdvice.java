package com.baidu.springbootstarter.config;

import com.baidu.springbootstarter.common.CommonException;
import com.baidu.springbootstarter.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public Result runtimeExceptionHandler(CommonException exception) {
        return exception.getResult();
    }
}
