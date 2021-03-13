package com.spring.practice.repository;

import com.spring.practice.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findFirst100();
    School findByName(@Param("name") String name);
}
