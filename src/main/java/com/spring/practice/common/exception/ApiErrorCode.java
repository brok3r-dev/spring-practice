package com.spring.practice.common.exception;

public enum ApiErrorCode {
    //region /common
    INTERNAL_SERVER_ERROR("알 수 없는 오류가 발생했습니다. 관계자에게 문의하세요."),
    INVALID_AUTH_VALUE("확인되지 않은 요청입니다."),
    //endregion

    //region /school
    SCHOOL_NOT_FOUND("학교를 찾을 수 없습니다."),
    SCHOOL_ALREADY_EXIST("이미 등록된 이름의 학교입니다."),
    SCHOOL_CANNOT_BE_DELETED("학생이 존재하여 학교를 제거할 수 없습니다."),
    //endregion

    //region /student
    STUDENT_NOT_FOUND("학생을 찾을 수 없습니다."),
    //endregion

    //region /teacher
    TEACHER_NOT_FOUND("선생님을 찾을 수 없습니다.");
    //endregion

    private final String message;

    ApiErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}