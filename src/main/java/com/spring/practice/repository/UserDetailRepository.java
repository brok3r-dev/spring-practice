package com.spring.practice.repository;

import com.spring.practice.entity.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    UserDetail findByUsername(@Param("username") String username);
}
