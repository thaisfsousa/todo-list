package com.example.todo.useCases.Todo;


import com.example.todo.domain.Todo;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTodo {

    private final TodoRepository todoRepository;

    public List<Todo> execute(){
        return todoRepository.findAll();
    }
}
