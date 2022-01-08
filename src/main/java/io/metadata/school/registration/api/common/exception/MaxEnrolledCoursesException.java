package io.metadata.school.registration.api.common.exception;

/**
 * Custom Exception for triggering Http 406 NOT_ACCEPTABLE response.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public class MaxEnrolledCoursesException extends Exception {
    public MaxEnrolledCoursesException() {
    }

    public MaxEnrolledCoursesException(String message) {
        super(message);
    }
}
