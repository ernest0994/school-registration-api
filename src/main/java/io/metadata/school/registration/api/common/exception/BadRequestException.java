package io.metadata.school.registration.api.common.exception;

/**
 * Custom Exception for triggering Http 400 response.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public class BadRequestException extends Exception {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
