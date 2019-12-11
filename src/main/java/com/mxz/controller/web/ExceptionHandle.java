package com.mxz.controller.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
*@Description  
*@author mxz
*2018-8-8
**/
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Exception handleException(HttpServletResponse response, Exception exception) {
        return exception;
    }
}
