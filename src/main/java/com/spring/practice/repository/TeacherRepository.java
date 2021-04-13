package com.spring.practice.repository;

import com.spring.practice.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByNameAndSchoolId(@Param("name") String name, @Param("school_id") Long schoolId);
}
