package com.tineo.wallet_backend.exception;

import com.tineo.wallet_backend.config.Constant;
import com.tineo.wallet_backend.dto.global.GlobalResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Objects;


@ControllerAdvice
public class GlobalExceptionHandler {
    // ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        GlobalResponse errorResponse = createErrorResponse(ex, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // EntityAlreadyExistsException
    @ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<GlobalResponse> handleEntityAlreadyExistsException(EntityAlreadyExists ex, HttpServletRequest request) {
        GlobalResponse errorResponse = createErrorResponse(ex, request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GlobalResponse> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        GlobalResponse errorResponse = createErrorResponse(ex, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();

        GlobalResponse errorResponse = createErrorResponse(ex, request);
        errorResponse.setMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // MethodArgumentMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String parameterName = ex.getName();
        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido";
        String message = String.format(Constant.GLOBAL_RESPONSE_ERROR_PARAMETER_TYPE, parameterName, expectedType);

        GlobalResponse errorResponse = createErrorResponse(ex, request);
        errorResponse.setMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse> handleInternalServerError(Exception ex, HttpServletRequest request) {
        GlobalResponse errorResponse = createErrorResponse(ex, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // Private methods
    private GlobalResponse createErrorResponse(Exception ex, HttpServletRequest request) {
        return GlobalResponse.builder()
                .ok(false)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .details(String.format(Constant.GLOBAL_RESPONSE_ERROR_DETAILS, request.getMethod(), request.getRequestURI()))
                .build();
    }
}
