package com.dave.todosapi.controllers;

import com.dave.todosapi.TodoRepository;
import com.dave.todosapi.domain.Todo;
import com.dave.todosapi.api.v1.model.TodoDto;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository todoRepository) {
        this.repository = todoRepository;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return repository.findAll();
    }

    @GetMapping("{todoId}")
    public Optional<Todo> getTodoById(@PathVariable Long todoId) {
        return repository.findById(todoId);
    }

    @PostMapping
    public Todo newTodo(@RequestBody TodoDto dto) {

        Todo newTodo = new Todo();

        newTodo.setTitle(dto.getTitle());
        newTodo.setDescription(dto.getDescription());
        newTodo.setOwnerId(dto.getOwnerId());
        newTodo.setTodoStatus(0);
        newTodo.setCreated(Calendar.getInstance().getTime());
        newTodo.setModified(Calendar.getInstance().getTime());
        newTodo.setDueDate(dto.getDueDate());

        return repository.save(newTodo);
    }

    @PutMapping("{id}")
    public Todo putTodo(@RequestBody Todo newTodo, @PathVariable Long id) {
        return repository.findById(id)
                .map(todo -> {
                    todo.setTitle(newTodo.getTitle());
                    todo.setDescription(newTodo.getDescription());
                    todo.setOwnerId(newTodo.getOwnerId());
                    todo.setTodoStatus(newTodo.getTodoStatus());
                    todo.setModified(Calendar.getInstance().getTime());
                    todo.setDueDate(todo.getDueDate());
                    return repository.save(todo);
                })
                .orElseGet(() -> {
                    newTodo.setId(id);
                    return  repository.save(newTodo);
                });
    }

    @DeleteMapping("{todoId}")
    public void deleteTodo(@PathVariable Long todoId) {
        repository.deleteById(todoId);
    }
}
