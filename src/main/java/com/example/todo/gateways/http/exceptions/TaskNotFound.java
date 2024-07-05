package com.example.todo.gateways.http.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskNotFound extends TodoException {

    public TaskNotFound() {
        super("Task not Found.", HttpStatus.NOT_FOUND);
    }
}