package com.example.todo.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.todo.domain.Task;
import com.example.todo.domain.Todo;
import com.example.todo.gateways.repository.TodoRepository;
import com.example.todo.gateways.http.exceptions.TaskNotFound;
import com.example.todo.gateways.http.exceptions.TodoNotFound;
import com.example.todo.useCases.Task.RemoveTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class RemoveTaskTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private RemoveTask removeTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteWhenTodoExistsAndTaskExists() {
        String todoId = "0";
        String taskId = "1";
        Task task = new Task(taskId, "Task 1", false);
        Todo todo = new Todo(todoId);
        todo.getTasks().add(task);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todo));

        removeTask.execute(todoId, taskId);

        assertFalse(todo.getTasks().stream().anyMatch(t -> t.getId().equals(taskId)));
        verify(todoRepository, times(1)).save(todo);
    }

    @Test
    public void testExecuteWhenTodoExistsAndTaskDoesNotExist() {
        String todoId = "0";
        String taskId = "1";
        Todo todo = new Todo(todoId);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todo));

        assertThrows(TaskNotFound.class, () -> {
            removeTask.execute(todoId, taskId);
        });

        verify(todoRepository, never()).save(todo);
    }

    @Test
    public void testExecuteWhenTodoDoesNotExist() {
        String todoId = "0";
        String taskId = "1";

        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        assertThrows(TodoNotFound.class, () -> {
            removeTask.execute(todoId, taskId);
        });

        verify(todoRepository, never()).save(any(Todo.class));
    }
}
