package org.ace.insurance.outbound_insurance.exception;


import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import org.ace.insurance.outbound_insurance.exception.custom.BadRequestException;
import org.ace.insurance.outbound_insurance.exception.custom.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import utility.HttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice
public class CustomExceptionHandler implements ErrorController {
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint.Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request.";
    private static final String ERROR_PROCESSING_FILE_MSG = "Error Occurred while processing file.";
    public static final String NO_HANDLER_MSG = "There is no mapping for this URL.";
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = HttpMethod.valueOf(Arrays.stream(Objects.requireNonNull(exception.getSupportedMethods())).iterator().next());
        return createHttpResponse(HttpStatus.METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<HttpResponse> noResourceFoundException(NoResourceFoundException exception) {
        return createHttpResponse(HttpStatus.BAD_REQUEST, NO_HANDLER_MSG);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(HttpServletRequest request, Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpResponse> illegalArgumentException(IllegalArgumentException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE_MSG);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpResponse> badRequestException(BadRequestException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        HttpResponse httpResponse = new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message);
        return new ResponseEntity<>(httpResponse, httpStatus);
    }

}
