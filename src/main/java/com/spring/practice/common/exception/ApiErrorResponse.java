package com.spring.practice.common.exception;

import lombok.Getter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
public class ApiErrorResponse {
    private final ApiErrorCode code;
    private final String message;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date timestamp;

    public ApiErrorResponse(ApiErrorCode code, String message, Date timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
}
