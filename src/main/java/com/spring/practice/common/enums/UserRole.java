package com.spring.practice.common.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.spring.practice.common.enums.UserPermission.*;

/** 유저 권한
 * 유저마다 사용할 수 있는 권한을 저장하는 클래스
 * UserRole 또는 UserPermission 을 이용하여 권한 설정
 */
public enum UserRole {
    ADMIN(Sets.newHashSet(SCHOOL_WRITE, SCHOOL_READ, TEACHER_WRITE, TEACHER_READ, STUDENT_WRITE, STUDENT_READ)),
    MANAGER(Sets.newHashSet(SCHOOL_READ, TEACHER_WRITE, TEACHER_READ, STUDENT_WRITE, STUDENT_READ)),
    USER(Sets.newHashSet(SCHOOL_READ, STUDENT_READ));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
