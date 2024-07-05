package com.example.todo.gateways.http;

import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TodoResponseDTO;
import com.example.todo.useCases.AddTask;
import com.example.todo.useCases.UpdateTask;
import com.example.todo.useCases.GetTask;
import com.example.todo.useCases.RemoveTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todo")
public class TodoController {

    private final GetTask getTask;
    private final AddTask addTask;
    private final UpdateTask updateTask;
    private final RemoveTask removeTask;

    @Autowired
    public TodoController(GetTask getTask,
                          AddTask addTask,
                          UpdateTask updateTask,
                          RemoveTask removeTask) {
        this.getTask = getTask;
        this.addTask = addTask;
        this.updateTask = updateTask;
        this.removeTask = removeTask;
    }

    @Operation(description = "" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                        description = "Returns all Tasks."),
    })
    @GetMapping(path = "tasks")
    public List<TodoResponseDTO> getTasks(){
        return getTask.execute();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Returns a new Todo List, adding a new task.")
    })
    @PostMapping(path = "tasks")
    public TodoResponseDTO saveProduct(@Valid @RequestBody final TodoResponseDTO task) {
        return addTask.execute(task);
    }

    @PutMapping(path = "tasks/{id}")
    public TodoResponseDTO updateProduct(@Valid @RequestBody final TodoResponseDTO task, @PathVariable Long id) {
        return updateTask.execute(id, task);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Task was successfully deleted."),
            @ApiResponse(responseCode = "404",
                    description = "Task was not found.")
    })
    @DeleteMapping(path = "tasks/remove/{id}")
    public String removeProduct(@PathVariable final Long id){
        removeTask.execute(id);
        return("Product deleted");
    }
}
