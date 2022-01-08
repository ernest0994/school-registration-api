package io.metadata.school.registration.api.controller;

import io.metadata.school.registration.api.common.dto.CourseDTO;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Course;
import io.metadata.school.registration.api.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles Http requests for Courses.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDTO> getAllCourses() throws NotFoundException {
        List<Course> courses = courseService.findAllCourses();
        if (ObjectUtils.isEmpty(courses))
            throw new NotFoundException();
        return courses.stream().map(course -> modelMapper.map(course, CourseDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{page}/{size}/{sortBy}")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDTO> getAllCourses(@PathVariable("page") int page,
                                         @PathVariable("size") int size,
                                         @PathVariable("sortBy") String sortBy) throws NotFoundException {
        Page<Course> courses = courseService.findAllCourses(PageRequest.of(page, size, Sort.by(sortBy)));
        if (courses.getContent().size() == 0)
            throw new NotFoundException();
        return courses.map(course -> modelMapper.map(course, CourseDTO.class)).getContent();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO getCourse(@PathVariable("id") Long id) throws NotFoundException {
        Course course = courseService.findCourseById(id).orElse(null);
        if (ObjectUtils.isEmpty(course))
            throw new NotFoundException();
        return modelMapper.map(course, CourseDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO saveCourse(@RequestBody @Valid CourseDTO courseDTO) {
        return modelMapper.map(courseService.saveCourse(modelMapper.map(courseDTO, Course.class)), CourseDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCourse(@PathVariable("id") Long id,
                             @RequestBody @Valid CourseDTO courseDTO) throws NotFoundException {
        courseService.updateCourse(id, courseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }
}
