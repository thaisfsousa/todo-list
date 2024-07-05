package com.example.todo.gateways.http.handler;

import com.example.todo.gateways.http.DTO.ExceptionResponseDTO;
import com.example.todo.gateways.http.exceptions.TodoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({TodoException.class})
    public ResponseEntity<ExceptionResponseDTO> handleException(final TodoException ex) {
        return new ResponseEntity<>(new ExceptionResponseDTO(ex.getMessage()), ex.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleInternalServerException(final Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(field -> "%s %s".formatted(field.getField(), field.getDefaultMessage())).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}