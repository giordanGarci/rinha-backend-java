package com.rinha.backend.transactions.exceptions;

import com.rinha.backend.transactions.services.exceptions.ValidationTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidationTransactionException.class)
    public ResponseEntity<Object> handleIncoherentTransactionException(ValidationTransactionException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        HttpStatus springHttpStatus = mapToSpringHttpStatus(ex.getStatusCode());

        return new ResponseEntity<>(body, springHttpStatus);
    }

    private HttpStatus mapToSpringHttpStatus(int statusCode) {
        switch (statusCode) {
            case 404:
                return HttpStatus.NOT_FOUND;
            case 422:
                return HttpStatus.UNPROCESSABLE_ENTITY;
            default:
                log.error("Unexpected status code: {}", statusCode);
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
