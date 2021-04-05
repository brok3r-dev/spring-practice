package com.spring.practice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        ApiErrorResponse rsp = new ApiErrorResponse(
                ApiErrorCode.INTERNAL_SERVER_ERROR,
                ex.getLocalizedMessage(),
                new Date()
        );

        return new ResponseEntity<>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        ApiErrorResponse rsp = new ApiErrorResponse(
                ex.getApiErrorCode(),
                ex.getMessage(),
                new Date()
        );

        return new ResponseEntity<>(rsp, ex.getHttpStatus());
    }
}
