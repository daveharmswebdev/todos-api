package com.dave.todosapi.service;

import com.dave.todosapi.TodoRepository;
import com.dave.todosapi.api.v1.mapper.TodoMapper;
import com.dave.todosapi.api.v1.model.TodoDto;
import com.dave.todosapi.domain.Todo;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoMapper todoMapper, TodoRepository todoRepository) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(todoMapper::todoToTodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodoById(Long id) {
        return todoRepository.findById(id)
                .map(todoMapper::todoToTodoDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public TodoDto createNewTodo(TodoDto todoDto) {
        Todo todo = todoMapper.todoDtoToTodo(todoDto);
        return saveAndReturnDto(todo);
    }

    private TodoDto saveAndReturnDto(Todo todo) {
        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.todoToTodoDto(savedTodo);
    }

    @Override
    public TodoDto saveTodoByDto(long id, TodoDto todoDto) {
        Todo todo = todoMapper.todoDtoToTodo(todoDto);
        todo.setId(id);
        return saveAndReturnDto(todo);
    }
}
