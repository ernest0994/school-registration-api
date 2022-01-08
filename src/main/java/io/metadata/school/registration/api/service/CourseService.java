package io.metadata.school.registration.api.service;

import io.metadata.school.registration.api.common.dto.CourseDTO;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Handles business logic definition for courses.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public interface CourseService {

    List<Course> findAllCourses();

    Page<Course> findAllCourses(Pageable pageable);

    Optional<Course> findCourseById(Long id);

    Course saveCourse(Course course);

    void updateCourse(Long id, CourseDTO courseDTO) throws NotFoundException;

    void deleteCourse(Long id);
}
