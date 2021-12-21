package com.dave.todosapi.service;

import com.dave.todosapi.TodoRepository;
import com.dave.todosapi.api.v1.mapper.TodoMapper;
import com.dave.todosapi.api.v1.model.TodoDto;
import com.dave.todosapi.domain.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        List<Todo> todos = Arrays.asList(new Todo(), new Todo(), new Todo());

        when(todoRepository.findAll()).thenReturn(todos);

        List<TodoDto> todoDtos = todoService.getAllTodos();

        assertEquals(3, todoDtos.size());
    }

    @Test
    public void getTodoById() throws Exception {
        // given
        Todo todo = new Todo();
        todo.setTitle(TITLE);
        todo.setDescription(DESCRIPTION);
        todo.setDueDate(DUE_DATE);

        when(todoRepository.findById(ID)).thenReturn(Optional.of(todo));

        // when
        TodoDto todoDto = todoService.getTodoById(ID);

        // then
        assertEquals(TITLE, todoDto.getTitle());
        assertEquals(DESCRIPTION, todoDto.getDescription());
        assertEquals(DUE_DATE, todoDto.getDueDate());
    }

    @Test
    public void createNewTodo() throws Exception {
        // given
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle(TITLE);
        todoDto.setDueDate(DUE_DATE);
        todoDto.setDescription(DESCRIPTION);

        Todo savedTodo = new Todo();
        savedTodo.setTitle(todoDto.getTitle());
        savedTodo.setDescription(todoDto.getDescription());
        savedTodo.setDueDate(todoDto.getDueDate());
        savedTodo.setId(ID);

        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);

        // when
        TodoDto savedDto = todoService.createNewTodo(todoDto);

        assertEquals(TITLE, savedDto.getTitle());
        assertEquals(DUE_DATE, savedDto.getDueDate());
        assertEquals(DESCRIPTION, savedDto.getDescription());
    }

    @Test
    public void saveTodoByDto() throws Exception {
        // given
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle(TITLE);
        todoDto.setDueDate(DUE_DATE);
        todoDto.setDescription(DESCRIPTION);

        Todo savedTodo = new Todo();
        savedTodo.setTitle(todoDto.getTitle());
        savedTodo.setDescription(todoDto.getDescription());
        savedTodo.setDueDate(todoDto.getDueDate());
        savedTodo.setId(ID);

        when(todoRepository.save(any(Todo.class))).thenReturn(savedTodo);

        // when
        TodoDto savedDto = todoService.saveTodoByDto(ID, todoDto);

        assertEquals(TITLE, savedDto.getTitle());
        assertEquals(DUE_DATE, savedDto.getDueDate());
        assertEquals(DESCRIPTION, savedDto.getDescription());
    }

    @Test
    public void deleteTodoById() throws Exception {
        Long id = 1L;

        todoRepository.deleteById(id);

        verify(todoRepository, times(1)).deleteById(anyLong());;
    }
}
