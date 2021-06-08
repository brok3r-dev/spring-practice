package com.spring.practice.entity;

import com.spring.practice.data.request.TeacherRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fcm_token")
    private String fcmToken;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    private School school;

    public Teacher(TeacherRequest tt, School sc) {
        this.name = tt.getName();
        this.address = tt.getAddress();
        this.phoneNumber = tt.getPhoneNumber();
        this.fcmToken = tt.getFcmToken();
        this.school = sc;
    }

    public void updateTeacher(TeacherRequest tt) {
        this.name = tt.getName();
        this.address = tt.getAddress();
        this.phoneNumber = tt.getPhoneNumber();
        this.fcmToken = tt.getFcmToken();
    }

    public void moveSchool(School s) {
        this.school = s;
    }
}
