package com.example.todo.useCases.Task;

import com.example.todo.domain.Task;
import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TaskRequestDTO;
import com.example.todo.gateways.http.exceptions.TodoNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@Component
@RequiredArgsConstructor
public class AddTask {

    private final TodoRepository todoRepository;

    public void addOrUpdateTask(Task task, Todo todo){
        int taskIndex = IntStream.range(0, todo.getTasks().size())
                .filter(t -> todo.getTasks().get(t).getId().equals(task.getId()))
                .findFirst().orElse(-1);
        if (taskIndex != -1)
            todo.getTasks().set(taskIndex, task);
        else
            todo.getTasks().add(task);
    }

    public Task execute(String todoId, TaskRequestDTO task) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(TodoNotFound::new);
        Task newTask = new Task(task);
        addOrUpdateTask(newTask, todo);
        todoRepository.save(todo);
        return newTask;
    }
}
