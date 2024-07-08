package com.example.todo.gateways.http;

import com.example.todo.domain.Task;
import com.example.todo.gateways.http.DTO.TaskRequestDTO;
import com.example.todo.useCases.Task.AddTask;
import com.example.todo.useCases.Task.GetTask;
import com.example.todo.useCases.Task.RemoveTask;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todo/{todoId}/tasks")
public class TaskController {

    private final GetTask getTask;
    private final AddTask addTask;
    private final RemoveTask removeTask;

    @Autowired
    public TaskController(GetTask getTask,
                          AddTask addTask,
                          RemoveTask removeTask) {
        this.getTask = getTask;
        this.addTask = addTask;
        this.removeTask = removeTask;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returns all Tasks from specific Todo."),
    })
    @GetMapping
    public List<Task> getTask(@PathVariable String todoId){
        return getTask.execute(todoId);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Returns a new Task, added to specific Todo.")
    })
    @PostMapping
    public Task saveTask(@PathVariable String todoId, @Valid @RequestBody final TaskRequestDTO task) {
        return addTask.execute(todoId, task);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Task was successfully deleted from Todo."),
            @ApiResponse(responseCode = "404",
                    description = "Task was not found in Todo.")
    })
    @DeleteMapping(path = "/{taskId}")
    public String removeTask(@PathVariable String todoId, @PathVariable final String taskId){
        removeTask.execute(todoId, taskId);
        return("Task deleted");
    }

}
