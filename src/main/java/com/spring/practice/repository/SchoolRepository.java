package com.spring.practice.repository;

import com.spring.practice.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    School findByName(@Param("name") String name);
}
