package com.dave.todosapi.controllers.v1;

import com.dave.todosapi.api.v1.model.TodoDto;
import com.dave.todosapi.controllers.RestResponseEntityExceptionHandler;
import com.dave.todosapi.controllers.TodoController;
import com.dave.todosapi.service.ResourceNotFoundException;
import com.dave.todosapi.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoControllersTest extends AbstractControllerTest {
    private static final Timestamp DUE_DATE = Timestamp.valueOf("2022-01-05 08:00:00");

    @Mock
    TodoService todoService;

    @InjectMocks
    TodoController todoController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(todoController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
    }

    @Test
    public void getAllTodos() throws Exception {
        // given
        TodoDto todo1 = new TodoDto();
        todo1.setTitle("title 1");
        todo1.setDescription("description 1");
        todo1.setDueDate(DUE_DATE);

        TodoDto todo2 = new TodoDto();
        todo2.setTitle("title 2");
        todo2.setDescription("description 2");
        todo2.setDueDate(DUE_DATE);

        TodoDto todo3 = new TodoDto();
        todo3.setTitle("title 3");
        todo3.setDescription("description 3");
        todo3.setDueDate(DUE_DATE);

        List<TodoDto> todos = Arrays.asList(todo1, todo2, todo3);

        when(todoService.getAllTodos()).thenReturn(todos);

        // when and assert
        mockMvc.perform(get("/api/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.todos", hasSize(3)));

    }

    @Test
    public void getTodoById() throws Exception {
        // when
        TodoDto todo1 = new TodoDto();
        todo1.setTitle("title 1");
        todo1.setDescription("description 1");
        todo1.setOwnerId(0L);
        todo1.setDueDate(DUE_DATE);

        when(todoService.getTodoById(1L)).thenReturn(todo1);

        mockMvc.perform(get("/api/todos/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("title 1")));

    }

    @Test
    public void createNewTodo() throws Exception {
        // when
        TodoDto todo1 = new TodoDto();
        todo1.setTitle("title 1");
        todo1.setDescription("description 1");
        todo1.setOwnerId(0L);
        todo1.setDueDate(DUE_DATE);

        TodoDto returnDto = new TodoDto();
        returnDto.setTitle(todo1.getTitle());
        returnDto.setDescription(todo1.getDescription());
        returnDto.setOwnerId(todo1.getOwnerId());
        returnDto.setDueDate(todo1.getDueDate());

        // was necessary to use any(TodoDto.class) in order to pass
        when(todoService.createNewTodo(any(TodoDto.class))).thenReturn(returnDto);

//        Really useful for debugging;
//        String content = asJsonString(todo1);
//
//        String response = mockMvc.perform(post("/api/todos")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content))
//                .andReturn().getResponse().getContentAsString();
//
//        System.out.println(response);

        mockMvc.perform(post("/api/todos").contentType(MediaType.APPLICATION_JSON).content(asJsonString(todo1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", equalTo("description 1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/todos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        when(todoService.getTodoById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/todos/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
