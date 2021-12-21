package com.dave.todosapi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TodoDto {

    private String title;
    private String description;
    private Long ownerId;
    private Date dueDate;

    @JsonProperty("todo_url")
    private String todoUrl;

    public TodoDto() {
    }

    public TodoDto(String title, String description, Long ownerId, Date dueDate) {
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTodoUrl() {
        return todoUrl;
    }

    public void setTodoUrl(String todoUrl) {
        this.todoUrl = todoUrl;
    }
}
