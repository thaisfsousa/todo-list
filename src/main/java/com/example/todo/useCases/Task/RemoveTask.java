package com.example.todo.useCases.Task;

import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.exceptions.TaskNotFound;
import com.example.todo.gateways.http.exceptions.TodoNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveTask {

    private final TodoRepository todoRepository;

    public Boolean taskExists(String taskId, Todo todo){
        return todo.getTasks().stream().anyMatch(task -> task.getId().equals(taskId));
    }

    public void execute(String todoId, String taskId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFound::new);
        if (!taskExists(taskId, todo))
            throw new TaskNotFound();
        todo.getTasks().removeIf(task -> task.getId().equals(taskId));
        todoRepository.save(todo);
    }
}
