package com.example.todo.domain;

import com.example.todo.gateways.http.DTO.TodoResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Boolean completed;


    public Todo(Todo task, long id){
        this.description = task.getDescription();
        this.completed = task.getCompleted();
        this.id = id;
    }

    public Todo(TodoResponseDTO task){
        this.description = task.getDescription();
        this.completed = task.getCompleted();
    }
    public Todo(){}
}
