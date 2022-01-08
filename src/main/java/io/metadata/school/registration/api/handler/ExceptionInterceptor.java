package io.metadata.school.registration.api.handler;

import io.metadata.school.registration.api.common.exception.BadRequestException;
import io.metadata.school.registration.api.common.exception.MaxEnrolledCoursesException;
import io.metadata.school.registration.api.common.exception.MaxEnrolledStudentsException;
import io.metadata.school.registration.api.common.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Provides error handling for rest controllers.
 *
 * @author Ernesto Acosta
 * @since 1/7/2022
 */
@RestControllerAdvice
public class ExceptionInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected @ResponseBody
    String handleCustomNotFoundExceptionRequest(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected @ResponseBody
    String handleBadRequestExceptionRequest(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(value = {MaxEnrolledCoursesException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected @ResponseBody
    String handleCustomMaxEnrolledCoursesRequest(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(value = {MaxEnrolledStudentsException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected @ResponseBody
    String handleCustomMaxEnrolledStudentsRequest(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected @ResponseBody
    String handleCustomNotAcceptableExceptionRequest(Exception ex, WebRequest request) {
        logger.error(ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }
}
