package io.metadata.school.registration.api.common.exception;

/**
 * Custom Exception for triggering Http 404 response.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public class NotFoundException extends Exception {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
