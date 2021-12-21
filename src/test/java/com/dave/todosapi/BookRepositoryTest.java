package com.dave.todosapi;

import com.dave.todosapi.domain.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    void testFindAllTodos() {
        List<Todo> todos = todoRepository.findAll();

        assertThat(todos).isNotNull();
        assertThat(todos.size()).isGreaterThan(2);
    }
}
