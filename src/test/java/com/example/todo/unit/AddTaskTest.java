package com.example.todo.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.todo.domain.Task;
import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TaskRequestDTO;
import com.example.todo.gateways.http.exceptions.TaskNotFound;
import com.example.todo.gateways.http.exceptions.TodoNotFound;
import com.example.todo.gateways.repository.TodoRepository;
import com.example.todo.useCases.Task.AddTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class AddTaskTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private AddTask addTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTaskWhenTodoDoesnNotExist() {
        String todoId = "1";
        TaskRequestDTO task = new TaskRequestDTO("1", "Task 1", false);

        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        assertThrows(TodoNotFound.class, () -> {
            addTask.execute(todoId, task);
        });

        verify(todoRepository, never()).save(any(Todo.class));
    }

    @Test
    public void testUpdateExistingTaskInTodo() {
        String todoId = "1";
        Task existingTask = new Task("1", "Task 1", false);
        TaskRequestDTO updatedTask = new TaskRequestDTO("1", "Updated Task 1", true);
        Todo existingTodo = new Todo(todoId);
        existingTodo.getTasks().add(existingTask);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodo));

        Task result = addTask.execute(todoId, updatedTask);

        assertEquals(new Task(updatedTask), result);
        verify(todoRepository, times(1)).save(any(Todo.class));
    }

    @Test
    public void testAddNewTaskToExistingTodo() {
        String todoId = "2";
        Task existingTask = new Task("1", "Task 1", false);
        TaskRequestDTO newTask = new TaskRequestDTO("2", "New Task 2", false);
        Todo existingTodo = new Todo();
        existingTodo.getTasks().add(existingTask);

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(existingTodo));

        Task result = addTask.execute(todoId, newTask);

        assertEquals(result, new Task(newTask));
        verify(todoRepository, times(1)).save(any(Todo.class));
    }
}
