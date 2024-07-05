package com.example.todo.useCases;

import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TodoResponseDTO;
import com.example.todo.gateways.http.exceptions.TaskAlreadyExists;
import com.example.todo.gateways.http.exceptions.TaskNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class AddTask {

    private final TodoRepository todoRepository;

    public Boolean existTask(Todo task){
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().anyMatch(taskD -> taskD.getDescription().equals(task.getDescription()));
    }

    public TodoResponseDTO execute(TodoResponseDTO task) {
        Todo newTask = new Todo(task);
        if (existTask(newTask))
            throw new TaskAlreadyExists();
        todoRepository.save(newTask);
        return task;
    }
}
