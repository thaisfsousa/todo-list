package com.example.todo.gateways.http.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "taskId cannot be empty.")
    private String id;
    @NotBlank(message = "description cannot be empty.")
    private String description;
    private Boolean completed;

    public TaskRequestDTO(String id, String description, Boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    public TaskRequestDTO() {}
}
