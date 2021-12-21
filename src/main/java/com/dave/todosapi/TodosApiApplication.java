package com.dave.todosapi;

import com.dave.todosapi.api.v1.mapper.TodoMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodosApiApplication.class, args);
	}

}
