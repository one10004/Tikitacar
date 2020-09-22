package com.bigdata.tikitacar.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest request
            , HttpServletResponse response
            , Object handler
            , Exception exception) {

        return exception.getMessage();
    }

}
