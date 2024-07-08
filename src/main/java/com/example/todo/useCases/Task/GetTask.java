package com.example.todo.useCases.Task;

import com.example.todo.domain.Task;
import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.exceptions.TodoNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTask {

    private final TodoRepository todoRepository;

    public List<Task> execute(String todoId){
        Todo todo = todoRepository.findByTodoId(todoId).orElseThrow(TodoNotFound::new);
        return todo.getTasks();
    }
}
