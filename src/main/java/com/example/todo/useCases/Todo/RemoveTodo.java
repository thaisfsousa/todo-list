package com.example.todo.useCases.Todo;


import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.exceptions.TodoNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveTodo {

    private final TodoRepository todoRepository;

    public void execute(String todoId) {
        Todo oldTodo = todoRepository.findById(todoId).orElseThrow(TodoNotFound::new);
        todoRepository.delete(oldTodo);
    }
}
