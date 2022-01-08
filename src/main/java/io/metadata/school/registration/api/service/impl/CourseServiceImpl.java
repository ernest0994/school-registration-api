package io.metadata.school.registration.api.service.impl;

import io.metadata.school.registration.api.common.dto.CourseDTO;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Course;
import io.metadata.school.registration.api.persistence.repository.CourseRepository;
import io.metadata.school.registration.api.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * Handles business logic implementation for courses.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> findAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, CourseDTO courseDTO) throws NotFoundException {
        Course course = courseRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(course))
            throw new NotFoundException();
        course.setDescription(courseDTO.getDescription());
        course.setMnemonic(courseDTO.getMnemonic());
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
