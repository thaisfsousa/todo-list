package com.example.todo.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.todo.domain.Task;
import com.example.todo.domain.Todo;
import com.example.todo.gateways.repository.TodoRepository;
import com.example.todo.useCases.Task.GetTask;
import com.example.todo.gateways.http.exceptions.TodoNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class GetTaskTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private GetTask getTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteWhenTodoExists() {
        String todoId = "1";
        Todo expectedTodo = new Todo(todoId);

        when(todoRepository.findByTodoId(todoId)).thenReturn(Optional.of(expectedTodo));

        List<Task> result = getTask.execute(todoId);

        assertNotNull(result);
        assertEquals(expectedTodo.getTasks(), result);
        verify(todoRepository, times(1)).findByTodoId(todoId);
    }

    @Test
    public void testExecuteWhenTodoDoesNotExist() {
        String todoId = "1";

        when(todoRepository.findByTodoId(todoId)).thenReturn(Optional.empty());

        assertThrows(TodoNotFound.class, () -> {
            getTask.execute(todoId);
        });

        verify(todoRepository, times(1)).findByTodoId(todoId);
    }
}
