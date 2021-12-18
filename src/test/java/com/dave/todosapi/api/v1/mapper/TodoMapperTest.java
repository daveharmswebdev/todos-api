package com.dave.todosapi.api.v1.mapper;

import com.dave.todosapi.api.v1.model.TodoDto;
import com.dave.todosapi.domain.Todo;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoMapperTest {

    public static final Long ID = 1L;
    public static final String TITLE = "Title";
    public static final String DESCRIPTION = "Description";
    public static final Timestamp DUE_DATE = Timestamp.from(Instant.now());

    TodoMapper todoMapper = TodoMapper.INSTANCE;

    public TodoMapperTest() {
    }

    @Test
    public void todoToTodoDto() throws Exception {

        // given
        Todo todo = new Todo();
        todo.setId(ID);
        todo.setTitle(TITLE);
        todo.setDescription(DESCRIPTION);
        todo.setDueDate(DUE_DATE);

        // when
        TodoDto todoDto = todoMapper.todoToTodoDto(todo);

        // then
        assertEquals(TITLE, todoDto.getTitle());
        assertEquals(DESCRIPTION, todoDto.getDescription());
        assertEquals(DUE_DATE, todoDto.getDueDate());
    }

    @Test void todoDtoToTodo() throws Exception {
        // given
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle(TITLE);
        todoDto.setDescription(DESCRIPTION);
        todoDto.setDueDate(DUE_DATE);

        // when
        Todo todo = todoMapper.todoDtoToTodo(todoDto);

        // then
        assertEquals(TITLE, todo.getTitle());
        assertEquals(DESCRIPTION, todo.getDescription());
        assertEquals(DUE_DATE, todo.getDueDate());


    }
}
