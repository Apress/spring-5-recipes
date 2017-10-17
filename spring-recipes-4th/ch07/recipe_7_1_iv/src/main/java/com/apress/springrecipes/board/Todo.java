package com.apress.springrecipes.board;

import org.hibernate.validator.constraints.NotEmpty;

public class Todo {

    private Long id;

    private String owner;

    @NotEmpty
    private String description;

    private boolean completed = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {

        return String.format("Todo [id=%d, description=%s, owner=%s, completed=%b]%n",this.id, this.description, this.owner, this.completed);
    }
}
