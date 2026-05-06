package com.clinica.agendamento.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Captura erros de validação em @Valid (DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        Map<String, List<String>> response = new HashMap<>();
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Captura erros de validação em entidades (Hibernate Validator)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        Map<String, List<String>> response = new HashMap<>();
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
