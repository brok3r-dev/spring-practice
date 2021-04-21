package com.spring.practice.entity;

import com.spring.practice.data.request.SchoolRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "School")
@NoArgsConstructor
@Setter
@Getter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private Set<Student> students;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private Set<Teacher> teachers;

    public School(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public School(SchoolRequest s) {
        this.name = s.getName();
        this.address = s.getAddress();
        this.phoneNumber = s.getPhoneNumber();
    }

    public void updateSchool(SchoolRequest s) {
        this.name = s.getName();
        this.address = s.getAddress();
        this.phoneNumber = s.getPhoneNumber();
    }

    public void registerStudent(Student s) {
        if (students == null) { students = new HashSet<>(); }
        students.add(s);
    }

    public void unregisterStudent(Student s) {
        if (students != null) {
            students.remove(s);
        }
    }

    public void registerTeacher(Teacher t) {
        if (teachers == null) { teachers = new HashSet<>(); }
        teachers.add(t);
    }

    public void unregisterTeacher(Teacher t) {
        if (teachers != null) {
            teachers.remove(t);
        }
    }
}
