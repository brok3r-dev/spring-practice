package com.spring.practice.mapper;

import com.spring.practice.entity.School;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SchoolMapper {
    @Insert("INSERT INTO school(name, address, phone_number) VALUES(${name}, ${address}, ${phone_number})")
    int insertSchool(
            @Param("name") String name,
            @Param("address") String address,
            @Param("phone_number") String phoneNumber
    );

    @Update("UPDATE school SET address = ${address}, phone_number = ${phone_number} WHERE name = \"${name}\"")
    int update(
            @Param("name") String name,
            @Param("address") String address,
            @Param("phone_number") String phoneNumber
    );

    @Select("SELECT * FROM school")
    List<School> getAllSchools();

    @Select("SELECT * FROM school WHERE school.name = \"${name}\"")
    School getSchool(@Param("name") String name);

    @Select("SELECT count(school.id) FROM school")
    @ResultType(Integer.class)
    int getNumberOfSchool();

    @Delete("DELETE FROM school WHERE name = \"${name}\"")
    int deleteSchool(@Param("name") String name);
}
