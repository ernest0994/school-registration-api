package io.metadata.school.registration.api.service;

import io.metadata.school.registration.api.common.dto.CourseDTO;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Course;
import io.metadata.school.registration.api.persistence.repository.CourseRepository;
import io.metadata.school.registration.api.service.impl.CourseServiceImpl;
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
 * Test class for Course Service.
 *
 * @author Ernesto Acosta
 * @since 1/8/2021
 */
@ExtendWith(SpringExtension.class)
public class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;

    private CourseService courseService;

    @BeforeEach
    void setup() {
        courseService = new CourseServiceImpl(courseRepository);
    }

    @Test
    @DisplayName("Test All courses")
    public void findAllCoursesTest() {
        List<Course> courses = Arrays.asList(
                new Course("Test course 1", "TEST101"),
                new Course("Test course 2", "TEST102"));
        when(courseRepository.findAll()).thenReturn(courses);
        assertEquals(courses, courseService.findAllCourses());
    }

    @Test
    @DisplayName("Searches course by Id")
    public void findCourseByIdTest() {
        Course course = new Course("Test course", "TEST101");
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        assertEquals(Optional.of(course), courseService.findCourseById(anyLong()));
    }

    @Test
    @DisplayName("Saves a course")
    public void saveCourseTest() {
        Course course = new Course("Test course", "TEST101");
        when(courseRepository.save(course)).thenReturn(course);
        assertEquals(course, courseService.saveCourse(course));
    }

    @Test
    @DisplayName("Update a course")
    public void updateCourseTest() {
        Course course = new Course("Test course", "TEST101");
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        assertAll(() -> courseService.updateCourse(anyLong(), new CourseDTO("Test course updated", "TEST102")));
    }

    @Test
    @DisplayName("Make sure exception is thrown if no course is found in update")
    public void updateCourseExceptionTest() throws NotFoundException {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(
                NotFoundException.class,
                () -> courseService.updateCourse(
                        anyLong(),
                        new CourseDTO("Test course updated", "TEST102")));
    }
}
