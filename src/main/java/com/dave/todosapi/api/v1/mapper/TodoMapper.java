package com.dave.todosapi.api.v1.mapper;

import com.dave.todosapi.api.v1.model.TodoDto;
import com.dave.todosapi.domain.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDto todoToTodoDto(Todo todo);

    Todo todoDtoToTodo(TodoDto todoDto);
}
