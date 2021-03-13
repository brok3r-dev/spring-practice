package com.spring.practice.entity;

import com.spring.practice.data.request.SchoolRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "School")
@NoArgsConstructor
@Getter
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "school")
    private Set<Student> students;

    public School(SchoolRequest s) {
        this.name = s.getName();
        this.address = s.getAddress();
        this.phoneNumber = s.getPhoneNumber();
    }

    public void registerStudent(Student s) {
        if (students == null) {
            students = new HashSet<Student>();
        }
        students.add(s);
    }
}