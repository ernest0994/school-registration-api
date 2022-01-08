package io.metadata.school.registration.api.controller;

import io.metadata.school.registration.api.common.dto.StudentDTO;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.persistence.entity.Student;
import io.metadata.school.registration.api.service.StudentService;
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
 * Handles Http requests for Students.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getAllStudents() throws NotFoundException {
        List<Student> students = studentService.findAllStudents();
        if (ObjectUtils.isEmpty(students))
            throw new NotFoundException();
        return students.stream().map(student -> modelMapper.map(student, StudentDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{page}/{size}/{sortBy}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getAllStudents(@PathVariable("page") int page,
                                           @PathVariable("size") int size,
                                           @PathVariable("sortBy") String sortBy) throws NotFoundException {
        Page<Student> students = studentService.findAllStudents(PageRequest.of(page, size, Sort.by(sortBy)));
        if (students.getContent().size() == 0)
            throw new NotFoundException();
        return students.map(student -> modelMapper.map(student, StudentDTO.class)).getContent();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudent(@PathVariable("id") Long id) throws NotFoundException {
        Student student = studentService.findStudentsById(id).orElse(null);
        if (ObjectUtils.isEmpty(student))
            throw new NotFoundException();
        return modelMapper.map(student, StudentDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO saveStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return modelMapper.map(studentService.saveStudent(modelMapper.map(studentDTO, Student.class)), StudentDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@PathVariable("id") Long id,
                              @RequestBody @Valid StudentDTO studentDTO) throws NotFoundException {
        studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }
}
