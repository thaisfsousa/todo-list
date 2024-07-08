package com.example.todo.gateways.http.DTO;

import com.example.todo.domain.Task;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TodoRequestDTO {
    private String description;
    private Boolean completed;
    private List<Task> tasks = new ArrayList<>();

    public TodoRequestDTO(){}
}
