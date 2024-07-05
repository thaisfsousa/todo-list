package com.example.todo.gateways.http.DTO;

import com.example.todo.domain.Todo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TodoResponseDTO {

    private String description;
    private Boolean completed;

    public TodoResponseDTO(Todo taskList) {
        this.description = taskList.getDescription();
        this.completed = taskList.getCompleted();
    }

    public TodoResponseDTO() {}
}
