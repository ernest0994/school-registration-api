package io.metadata.school.registration.api.service;

import io.metadata.school.registration.api.common.dto.StudentRegistrationDTO;
import io.metadata.school.registration.api.common.exception.MaxEnrolledCoursesException;
import io.metadata.school.registration.api.common.exception.MaxEnrolledStudentsException;
import io.metadata.school.registration.api.common.exception.NotFoundException;

/**
 * Handles business logic definition for registration flow.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public interface RegistrationService {

    void registerStudent(StudentRegistrationDTO studentRegistrationDTO) throws NotFoundException, MaxEnrolledCoursesException, MaxEnrolledStudentsException;
}
