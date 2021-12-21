package com.dave.todosapi.service;

import com.dave.todosapi.TodoRepository;
import com.dave.todosapi.api.v1.mapper.TodoMapper;
import com.dave.todosapi.api.v1.model.TodoDto;
import com.dave.todosapi.domain.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
                .map(todo -> {
                    TodoDto todoDto = todoMapper.todoToTodoDto(todo);
                    todoDto.setTodoUrl("/api/todos/" + todo.getId());
                    return todoDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodoById(Long id) {
        return todoRepository.findById(id)
                .map(todo -> {
                    TodoDto todoDto = todoMapper.todoToTodoDto(todo);
                    todoDto.setTodoUrl("/api/todos/" + todo.getId());
                    return todoDto;
                })
                .orElseThrow(ResourceNotFoundException::new);
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

    @Override
    public void deleteTodoById(Long id) {
        getTodoById(id);
        todoRepository.deleteById(id);
    }
}
