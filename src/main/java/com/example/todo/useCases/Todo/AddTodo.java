package com.example.todo.useCases.Todo;

import com.example.todo.domain.Task;
import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TodoRequestDTO;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddTodo {

    private final TodoRepository todoRepository;

    public Todo execute(String todoId, TodoRequestDTO newTodo) {
        Todo newObj = new Todo(newTodo, todoId);
        Optional<Todo> todo = todoRepository.findByTodoId(todoId);
        if (todo.isEmpty())
            newObj = new Todo(newTodo, todoId);
        else {
            for (Task task : todo.get().getTasks()) {
                newObj.getTasks().add(task);
            }
        }
        todoRepository.save(newObj);
        return newObj;
    }
}
