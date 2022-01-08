package io.metadata.school.registration.api.common.exception;

/**
 * Custom Exception for triggering Http 406 NOT_ACCEPTABLE response.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public class MaxEnrolledStudentsException extends Exception {
    public MaxEnrolledStudentsException() {
    }

    public MaxEnrolledStudentsException(String message) {
        super(message);
    }
}
