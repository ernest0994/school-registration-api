package io.metadata.school.registration.api.service;

import io.metadata.school.registration.api.common.dto.StudentRegistrationDTO;
import io.metadata.school.registration.api.persistence.entity.Course;
import io.metadata.school.registration.api.persistence.entity.Student;
import io.metadata.school.registration.api.persistence.repository.CourseRepository;
import io.metadata.school.registration.api.persistence.repository.StudentRepository;
import io.metadata.school.registration.api.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test class for Registration Service.
 *
 * @author Ernesto Acosta
 * @since 1/8/2021
 */
@ExtendWith(SpringExtension.class)
public class RegistrationServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;

    private RegistrationService registrationService;

    @BeforeEach
    void setup() {
        registrationService = new RegistrationServiceImpl(studentRepository, courseRepository);
        Integer maxEnrolledCourses = 5;
        Integer maxEnrolledStudents = 50;
        ReflectionTestUtils.setField(registrationService, "MAX_ENROLLED_COURSES", maxEnrolledCourses);
        ReflectionTestUtils.setField(registrationService, "MAX_ENROLLED_STUDENTS", maxEnrolledStudents);
    }

    @Test
    @DisplayName("Register student in course")
    public void registerStudentTest() {
        Student student = new Student("TestName", "TestLastName", "Test@Test.com");
        Course course = new Course("Test course", "TEST101");
        student.setEnrolledCourses(new HashSet<>(List.of(course)));
        course.setEnrolledStudents(new HashSet<>(List.of(student)));
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(courseRepository.findByMnemonic(anyString())).thenReturn(course);
        assertAll(() -> registrationService.registerStudent(new StudentRegistrationDTO(anyLong(), "TEST101")));
    }
}
