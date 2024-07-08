package com.example.todo.gateways.http.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TodoNotFound extends TodoException {

    public TodoNotFound() {
        super("Todo not Found.", HttpStatus.NOT_FOUND);
    }
}