package com.learning.StudentManagement.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiError buildError(
            HttpStatus status,
            String message,
            HttpServletRequest request,
            Map<String, String> validationErrors) {

        return new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                validationErrors
        );
    }

    @ExceptionHandler(ResourseNotFound.class)
    public ResponseEntity<ApiError> handleNotFound(
            ResourseNotFound ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(
                buildError(status, ex.getMessage(), request, Map.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.putIfAbsent(
                    error.getField(),
                    error.getDefaultMessage());
        }

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(
                buildError(status, "Validation failed", request, errors));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleIntegrityViolation(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status).body(
                buildError(
                        status,
                        "A database constraint was violated. Check for duplicate email values.",
                        request,
                        Map.of()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleInvalidBody(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(
                buildError(
                        status,
                        "Request body is invalid or contains an incorrect data type",
                        request,
                        Map.of()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(
                buildError(
                        status,
                        "Invalid value for parameter: " + ex.getName(),
                        request,
                        Map.of()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(
            Exception ex,
            HttpServletRequest request) {

        // Log the actual exception server-side in production.
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(
                buildError(
                        status,
                        "An unexpected server error occurred",
                        request,
                        Map.of()));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        HttpStatus status = ex.getMessage() != null
                && ex.getMessage().toLowerCase().contains("email")
                ? HttpStatus.CONFLICT
                : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(
                buildError(status, ex.getMessage(), request, Map.of()));
    }

}