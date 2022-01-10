package io.metadata.school.registration.api.service;

import io.metadata.school.registration.api.common.dto.StudentDTO;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Student;
import io.metadata.school.registration.api.persistence.repository.StudentRepository;
import io.metadata.school.registration.api.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Test class for Student Service.
 *
 * @author Ernesto Acosta
 * @since 1/8/2021
 */
@ExtendWith(SpringExtension.class)
public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    private StudentService studentService;

    @BeforeEach
    public void setup() {
        studentService = new StudentServiceImpl(studentRepository);
    }

    @Test
    @DisplayName("Test All students")
    public void findAllStudentsTest() {
        List<Student> students = Arrays.asList(
                new Student("TestName", "TestLastName", "Test@Test.com"),
                new Student("TestName2", "TestLastName2", "Test2@Test.com"));
        when(studentRepository.findAll()).thenReturn(students);
        assertEquals(students, studentService.findAllStudents());
    }

    @Test
    @DisplayName("Searches student by Id")
    public void findStudentByIdTest() {
        Student student = new Student("TestName", "TestLastName", "Test@Test.com");
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        assertEquals(Optional.of(student), studentService.findStudentsById(anyLong()));
    }

    @Test
    @DisplayName("Saves a student")
    public void saveStudentTest() {
        Student student = new Student("TestName", "TestLastName", "Test@Test.com");
        when(studentRepository.save(student)).thenReturn(student);
        assertEquals(student, studentService.saveStudent(student));
    }

    @Test
    @DisplayName("Update a student")
    public void updateStudentTest() {
        Student student = new Student("TestName", "TestLastName", "Test@Test.com");
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        assertAll(() -> studentService.updateStudent(
                anyLong(),
                new StudentDTO("TestName updated", "TestLastName", "Test@Test.com")));
    }

    @Test
    @DisplayName("Make sure exception is thrown if no student is found in update")
    public void updateStudentExceptionTest() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(
                NotFoundException.class,
                () -> studentService.updateStudent(
                        anyLong(),
                        new StudentDTO("TestName updated", "TestLastName", "Test@Test.com")));
    }
}
