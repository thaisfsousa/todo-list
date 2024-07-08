package com.example.todo.domain;


import com.example.todo.gateways.http.DTO.TaskRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Task {

    private String id;
    private String description;
    private Boolean completed;


    public Task(String id, String description, Boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    public Task(TaskRequestDTO task){
        this.id = task.getId();
        this.description = task.getDescription();
        this.completed = task.getCompleted();
    }
    public Task(){}

}
