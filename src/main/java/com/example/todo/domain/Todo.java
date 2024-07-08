package com.example.todo.domain;

import com.example.todo.gateways.http.DTO.TodoRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Todo {

    @Id
    private String todoId;
    private String description;
    private Boolean completed;
    private List<Task> tasks = new ArrayList<>();

    public Todo(Task task){
        this.tasks = new ArrayList<>();
        this.tasks.add(task);
    }

    public Todo(String todoId){
        this.todoId = todoId;
        this.completed = false;
        this.tasks = new ArrayList<>();
    }

    public Todo(TodoRequestDTO todo, String todoId){
        this.todoId = todoId;
        this.description = todo.getDescription();
        this.completed = todo.getCompleted();
        this.tasks = new ArrayList<>();
        this.tasks.addAll(todo.getTasks());
    }

    public Todo(){}
}
