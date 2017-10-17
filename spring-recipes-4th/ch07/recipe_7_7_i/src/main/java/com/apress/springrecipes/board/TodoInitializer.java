package com.apress.springrecipes.board;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
class TodoInitializer {

    private final TodoService todoService;

    TodoInitializer(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostConstruct
    public void setup() {

        // Simulate a logged in user
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("marten@ya2do.io", "", Collections.singletonList(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(token);

        Todo todo = new Todo();
        todo.setOwner("marten@ya2do.io");
        todo.setDescription("Finish Spring Recipes - Security Chapter");

        todoService.save(todo);

        todo = new Todo();
        todo.setOwner("marten@ya2do.io");
        todo.setDescription("Get Milk & Eggs");
        todo.setCompleted(true);
        todoService.save(todo);

        todo = new Todo();
        todo.setOwner("marten@ya2do.io");
        todo.setDescription("Call parents.");

        todoService.save(todo);

//        todo = new Todo();
//        todo.setOwner("jlong@pivotal.io");
//        todo.setDescription("Prepare Cloud Native Presentation");
//
//        todoService.save(todo);
//
//        todo = new Todo();
//        todo.setOwner("rwinch@pivotal.io");
//        todo.setDescription("Finish Spring Security Reactive.");
//
//        todoService.save(todo);

        SecurityContextHolder.clearContext();
    }
}
