package com.example.todo.gateways.http.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TodoException extends RuntimeException {
    private HttpStatus status;

    public TodoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
