package com.apress.springrecipes.board;

import java.util.List;

public interface TodoRepository {

    List<Todo> findAll();
    Todo findOne(long id);
    
    void remove(long id);
    Todo save(Todo todo);

    List<Todo> findByOwner(String author);

}
