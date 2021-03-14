package com.spring.practice.entity;

import com.spring.practice.common.enums.Grade;
import com.spring.practice.data.request.StudentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Student")
@NoArgsConstructor
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    public Student(StudentRequest st, School sc) {
        this.name = st.getName();
        this.address = st.getAddress();
        this.phoneNumber = st.getPhoneNumber();
        this.grade = st.getGrade();
        this.school = sc;
    }

    public void updateStudent(StudentRequest st) {
        this.name = st.getName();
        this.address = st.getAddress();
        this.phoneNumber = st.getPhoneNumber();
        this.grade = st.getGrade();
    }

    public void moveSchool(School s) {
        this.school = s;
    }
}
