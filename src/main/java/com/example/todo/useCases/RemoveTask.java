package com.example.todo.useCases;

import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.exceptions.TaskNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RemoveTask {

    private final TodoRepository todoRepository;

    public Boolean taskExists(Long taskId, List<Todo> todo){
        return todo.stream().anyMatch(task -> task.getId().equals(taskId));
    }

    public void execute(Long id) {
        List<Todo> todoList = todoRepository.findAll();
        if (!taskExists(id, todoList))
            throw new TaskNotFound();
        todoRepository.deleteById(id);
    }
}
