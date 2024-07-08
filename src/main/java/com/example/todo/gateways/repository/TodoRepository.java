package com.example.todo.gateways.repository;

import com.example.todo.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    Optional<Todo> findByTodoId(String todoId);

}
