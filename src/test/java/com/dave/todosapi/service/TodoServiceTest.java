package com.dave.todosapi.service;

import com.dave.todosapi.TodoRepository;
import com.dave.todosapi.api.v1.mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;

public class TodoServiceTest {

    public static final Long ID = 1L;
    public static final String TITLE = "Title";
    public static final String DESCRIPTION = "Description";
    public static final Timestamp DUE_DATE = Timestamp.from(Instant.now());

    TodoService todoService;

    @Mock
    TodoRepository todoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        todoService = new TodoServiceImpl(TodoMapper.INSTANCE, todoRepository);
    }

    @Test
    public void getAllTodos() throws Exception {

    }

}
