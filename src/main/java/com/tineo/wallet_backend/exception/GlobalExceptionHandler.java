package com.tineo.wallet_backend.exception;

import com.tineo.wallet_backend.dto.GlobalResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


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

    // BadRequestException ! No verificado
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GlobalResponse> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        GlobalResponse errorResponse = createErrorResponse(ex, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // MethodArgumentNotValidException

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
                .details("[" + request.getMethod() + "] " + request.getRequestURI())
                .build();
    }
}
