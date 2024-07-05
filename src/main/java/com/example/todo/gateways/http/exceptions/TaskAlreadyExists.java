package com.example.todo.gateways.http.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TaskAlreadyExists extends TodoException {

    public TaskAlreadyExists(){
        super("Task already exists.", HttpStatus.NOT_FOUND);
    }
}
