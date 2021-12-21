package com.dave.todosapi.api.v1.model;

import java.util.List;

public class TodoListDto {
    List<TodoDto> todos;

    public TodoListDto() {
    }

    public List<TodoDto> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoDto> todos) {
        this.todos = todos;
    }
}
