package com.example.todo.useCases;

import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TodoResponseDTO;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetTask {

    private final TodoRepository todoRepository;

    public List<TodoResponseDTO> execute(){
        List<Todo> todoList = todoRepository.findAll();
        List<TodoResponseDTO> response = new ArrayList<>();
        for (Todo todoItem : todoList) {
            response.add(new TodoResponseDTO(todoItem));
        }
        return response;
    }
}
