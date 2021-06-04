package com.spring.practice.repository;

import com.spring.practice.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    School findByName(@Param("name") String name);
}
