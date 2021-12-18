package com.dave.todosapi.service;

import com.dave.todosapi.api.v1.model.TodoDto;

import java.util.List;

public interface TodoService {

    List<TodoDto> getAllTodos();

    TodoDto getTodoById(Long id);

    TodoDto createNewTodo(TodoDto todoDto);

    TodoDto saveTodoByDto(long id, TodoDto todoDto);
}
