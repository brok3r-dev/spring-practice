package com.spring.practice.common.exception;

public enum ApiErrorCode {
    //region /common
    INTERNAL_SERVER_ERROR("알 수 없는 오류가 발생했습니다. 관계자에게 문의하세요."),
    INVALID_AUTH_VALUE("확인되지 않은 요청입니다."),
    //endregion

    //region /school
    SCHOOL_NOT_FOUND("학교를 찾을 수 없습니다."),
    SCHOOL_ALREADY_EXIST("이미 등록된 학교입니다."),
    //endregion

    //region /student
    STUDENT_NOT_FOUND("학생을 찾을 수 없습니다.");
    //endregion

    String message;

    ApiErrorCode(String message) {
        this.message = message;
    }
}