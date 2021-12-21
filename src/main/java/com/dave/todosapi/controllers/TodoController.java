package com.dave.todosapi.controllers;

import com.dave.todosapi.TodoRepository;
import com.dave.todosapi.api.v1.model.TodoListDto;
import com.dave.todosapi.domain.Todo;
import com.dave.todosapi.api.v1.model.TodoDto;
import com.dave.todosapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

//    @GetMapping
//    public ResponseEntity<List<TodoDto>> getTodos() {
//        List<TodoDto> todos = todoService.getAllTodos();
//        return new ResponseEntity<List<TodoDto>>(todos, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<TodoListDto> getTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        TodoListDto response = new TodoListDto();
        response.setTodos(todos);
        return new ResponseEntity<TodoListDto>(response, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long todoId) {
        return new ResponseEntity<TodoDto>(todoService.getTodoById(todoId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoDto> createNewTodo(@RequestBody TodoDto todoDto) {

        TodoDto fromService = todoService.createNewTodo(todoDto);

        return new ResponseEntity<TodoDto>(fromService, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<TodoDto>(todoService.saveTodoByDto(id, todoDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
