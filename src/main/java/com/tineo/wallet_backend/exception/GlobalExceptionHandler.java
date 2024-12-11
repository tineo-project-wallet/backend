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

    // EntityAlreadyExistsException

    // BadRequestException

    // MethodArgumentNotValidException

    // Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse> handleInternalServerError(Exception ex, HttpServletRequest request) {
        GlobalResponse errorResponse = createErrorResponse(ex, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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
