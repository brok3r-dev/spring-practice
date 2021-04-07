package com.spring.practice;

import com.spring.practice.common.enums.Grade;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("JUnit 5 Example")
class PracticeApplicationTests {
	@Mock
	private SchoolRepository schoolRepository;

    @Mock
	private StudentRepository studentRepository;

    @Test
	@DisplayName("Test for school registration and deletion")
    void RegisterSchool() {
        //given
        School schoolA = new School("Test School A", "Test Address A", "00000000");
        School schoolB = new School("Test School B", "Test Address B", "11111111");

        //when
		School resultA = schoolRepository.save(schoolA);
		School resultB = schoolRepository.save(schoolB);

        //then
		assertAll("result A", () -> {
			assertEquals(resultA.getName(), schoolA.getName(), "name is different");
			assertEquals(resultA.getAddress(), schoolA.getAddress(), "address is different");
			assertEquals(resultA.getPhoneNumber(), schoolA.getPhoneNumber(), "phone number is different");
		});

		assertAll("result B", () -> {
			assertEquals(resultB.getName(), schoolB.getName(), "name is different");
			assertEquals(resultB.getAddress(), schoolB.getAddress(), "address is different");
			assertEquals(resultB.getPhoneNumber(), schoolB.getPhoneNumber(), "phone number is different");
		});

		schoolRepository.delete(resultA);
		schoolRepository.delete(resultB);

		assertFalse(schoolRepository.findById(resultA.getId()).isPresent(), "result A was not removed");
		assertFalse(schoolRepository.findById(resultB.getId()).isPresent(), "result B was not removed");
    }

    @Test
	@DisplayName("Test for student registration and deletion")
    void RegisterStudent() {
        //given
        Student studentA = new Student("Test A", "Test A", "00000000", Grade.E_SECOND, "");
        Student studentB = new Student("Test B", "Test B", "11111111", Grade.H_FIRST, "");

        //when
        Student resultA = studentRepository.save(studentA);
        Student resultB = studentRepository.save(studentB);

        //then
		assertAll("result A", () -> {
			assertEquals(resultA.getName(), studentA.getName(), "name is different");
			assertEquals(resultA.getAddress(), studentA.getAddress(), "address is different");
			assertEquals(resultA.getPhoneNumber(), studentA.getPhoneNumber(), "phone number is different");
			assertEquals(resultA.getGrade(), studentA.getGrade(), "grade is different");
		});

		assertAll("result B", () -> {
			assertEquals(resultB.getName(), studentB.getName(), "name is different");
			assertEquals(resultB.getAddress(), studentB.getAddress(), "address is different");
			assertEquals(resultB.getPhoneNumber(), studentB.getPhoneNumber(), "phone number is different");
			assertEquals(resultB.getGrade(), studentB.getGrade(), "grade is different");
		});

		studentRepository.delete(resultA);
		studentRepository.delete(resultB);

		assertFalse(studentRepository.findById(resultA.getId()).isPresent());
		assertFalse(studentRepository.findById(resultB.getId()).isPresent());
    }
}
