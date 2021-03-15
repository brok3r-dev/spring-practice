package com.spring.practice.mapper;

import com.spring.practice.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student")
    List<Student> getAllStudents();

    @Select("SELECT * FROM student WHERE name = \"${name}\"")
    List<Student> getStudents(@Param("name") String name);

    @Select("SELECT * FROM student WHERE id = ${id}")
    Student getStudent(@Param("id") Long id);

    @Delete("DELETE * FROM student WHERE id = ${id}")
    int deleteStudent(@Param("id") Long id);
}
