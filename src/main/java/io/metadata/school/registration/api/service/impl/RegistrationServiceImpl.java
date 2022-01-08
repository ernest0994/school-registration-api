package io.metadata.school.registration.api.service.impl;

import io.metadata.school.registration.api.common.dto.StudentRegistrationDTO;
import io.metadata.school.registration.api.common.exception.MaxEnrolledCoursesException;
import io.metadata.school.registration.api.common.exception.MaxEnrolledStudentsException;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Course;
import io.metadata.school.registration.api.persistence.entity.Student;
import io.metadata.school.registration.api.persistence.repository.CourseRepository;
import io.metadata.school.registration.api.persistence.repository.StudentRepository;
import io.metadata.school.registration.api.service.RegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

/**
 * Handles business logic implementation for registration flow.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    @Value("${student.max.enrolled.courses}")
    private Integer MAX_ENROLLED_COURSES;
    @Value("${course.max.enrolled.students}")
    private Integer MAX_ENROLLED_STUDENTS;

    public RegistrationServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public void registerStudent(StudentRegistrationDTO studentRegistrationDTO) throws NotFoundException, MaxEnrolledCoursesException, MaxEnrolledStudentsException {
        Student student = studentRepository.findById(studentRegistrationDTO.getStudentId()).orElse(null);
        if (ObjectUtils.isEmpty(student)) throw new NotFoundException("Student not found");
        Course course = courseRepository.findByMnemonic(studentRegistrationDTO.getMnemonic());
        if (ObjectUtils.isEmpty(course)) throw new NotFoundException("Course not found");

        if (student.getEnrolledCourses().size() >= MAX_ENROLLED_COURSES)
            throw new MaxEnrolledCoursesException("Cannot add any more courses. Capacity exceeded");
        else if (course.getEnrolledStudents().size() >= MAX_ENROLLED_STUDENTS)
            throw new MaxEnrolledStudentsException("Cannot add more students. Capacity exceeded");

        student.getEnrolledCourses().add(course);
    }
}
