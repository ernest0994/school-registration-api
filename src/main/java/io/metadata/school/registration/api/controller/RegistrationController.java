package io.metadata.school.registration.api.controller;

import io.metadata.school.registration.api.common.dto.StudentRegistrationDTO;
import io.metadata.school.registration.api.common.exception.MaxEnrolledCoursesException;
import io.metadata.school.registration.api.common.exception.MaxEnrolledStudentsException;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import io.metadata.school.registration.api.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Handles Http requests for registration flow.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@RestController
@RequestMapping("/studentRegistration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registerStudent(@RequestBody @Valid StudentRegistrationDTO registrationDTO) throws NotFoundException, MaxEnrolledCoursesException, MaxEnrolledStudentsException {
        registrationService.registerStudent(registrationDTO);
    }
}
