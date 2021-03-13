package com.spring.practice.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final ApiErrorCode apiErrorCode;
    private final HttpStatus httpStatus;

    public ApiException(ApiErrorCode apiErrorCode, HttpStatus httpStatus) {
        super(apiErrorCode.message);
        this.apiErrorCode = apiErrorCode;
        this.httpStatus = httpStatus;
    }
}
