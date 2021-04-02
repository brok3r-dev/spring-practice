package com.spring.practice.common.enums;

public enum UserPermission {
    SCHOOL_WRITE("school:write"),
    SCHOOL_READ("school:read"),
    STUDENT_WRITE("student:write"),
    STUDENT_READ("student:read");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
