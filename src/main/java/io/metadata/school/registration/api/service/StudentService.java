package io.metadata.school.registration.api.service;

import io.metadata.school.registration.api.common.dto.StudentDTO;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Handles business logic definition for students.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public interface StudentService {

    List<Student> findAllStudents();

    Page<Student> findAllStudents(Pageable pageable);

    Optional<Student> findStudentsById(Long id);

    Student saveStudent(Student student);

    void updateStudent(Long id, StudentDTO studentDTO) throws NotFoundException;

    void deleteStudent(Long id);
}
