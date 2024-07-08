package com.example.todo.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.todo.domain.Task;
import com.example.todo.domain.Todo;
import com.example.todo.gateways.http.DTO.TodoRequestDTO;
import com.example.todo.gateways.repository.TodoRepository;
import com.example.todo.useCases.Todo.AddTodo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class AddTodoTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private AddTodo addTodo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteWhenTodoDoesNotExist() {
        String todoId = "1";
        TodoRequestDTO newTodo = new TodoRequestDTO();

        when(todoRepository.findByTodoId(todoId)).thenReturn(Optional.empty());

        Todo result = addTodo.execute(todoId, newTodo);

        assertNotNull(result);
        assertEquals(new Todo(newTodo, todoId), result);
        verify(todoRepository, times(1)).save(result);
    }

    @Test
    public void testExecuteWhenTodoExists() {
        String todoId = "1";
        Todo existingTodo = new Todo(todoId);
        existingTodo.getTasks().add(new Task("1", "Existing Task", false));

        TodoRequestDTO newTodo = new TodoRequestDTO();
        newTodo.getTasks().add(new Task("2", "New Task", false));

        when(todoRepository.findByTodoId(todoId)).thenReturn(Optional.of(existingTodo));

        Todo result = addTodo.execute(todoId, newTodo);

        assertNotNull(result);
        assertEquals(todoId, result.getTodoId());
        assertEquals(2, result.getTasks().size());
        assertTrue(result.getTasks().stream().anyMatch(task -> task.getId().equals("1")));
        assertTrue(result.getTasks().stream().anyMatch(task -> task.getId().equals("2")));
        verify(todoRepository, times(1)).save(result);
    }
}
