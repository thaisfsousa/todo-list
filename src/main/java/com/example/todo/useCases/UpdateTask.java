package com.example.todo.useCases;

import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TodoResponseDTO;
import com.example.todo.gateways.http.exceptions.TaskNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateTask {

    private final TodoRepository todoRepository;

    public TodoResponseDTO execute(Long id, TodoResponseDTO task) {
        Optional<Todo> oldTask = todoRepository.findById(id);
        if (oldTask.isEmpty()) {
            throw new TaskNotFound();
        }
        Todo newTask = new Todo(task);
        newTask.setId(oldTask.get().getId());
        todoRepository.save(newTask);
        return task;
    }
}
