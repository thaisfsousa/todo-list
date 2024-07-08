package com.example.todo.gateways.http;

import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TodoRequestDTO;
import com.example.todo.useCases.Todo.AddTodo;
import com.example.todo.useCases.Todo.GetTodo;
import com.example.todo.useCases.Todo.RemoveTodo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todo")
public class TodoController {

    private final GetTodo getTodo;
    private final AddTodo addTodo;
    private final RemoveTodo removeTodo;

    @Autowired
    public TodoController(GetTodo getTodo,
                          AddTodo addTodo,
                          RemoveTodo removeTodo) {
        this.getTodo = getTodo;
        this.addTodo = addTodo;
        this.removeTodo = removeTodo;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returns Todo."),
    })
    @GetMapping
    public List<Todo> getTodo(){
        return getTodo.execute();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Returns a new or updated Todo List.")
    })
    @PostMapping(path = "/{todoId}")
    public Todo saveTask(@PathVariable String todoId, @Valid @RequestBody final TodoRequestDTO todo) {
        return addTodo.execute(todoId, todo);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Todo was successfully deleted."),
            @ApiResponse(responseCode = "404",
                    description = "Todo was not found.")
    })
    @DeleteMapping(path = "/{todoId}")
    public String removeTodo(@PathVariable String todoId){
        removeTodo.execute(todoId);
        return("Todo deleted");
    }
}
