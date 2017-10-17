package com.apress.springrecipes.board;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
class TodoInitializer {

    private final TodoService messageBoardService;

    TodoInitializer(TodoService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }

    @PostConstruct
    public void setup() {

        Todo todo = new Todo();
        todo.setOwner("marten@ya2do.io");
        todo.setDescription("Finish Spring Recipes - Security Chapter");

        messageBoardService.save(todo);

        todo = new Todo();
        todo.setOwner("marten@ya2do.io");
        todo.setDescription("Get Milk & Eggs");
        todo.setCompleted(true);
        messageBoardService.save(todo);

        todo = new Todo();
        todo.setOwner("marten@ya2do.io");
        todo.setDescription("Call parents.");

        messageBoardService.save(todo);

        todo = new Todo();
        todo.setOwner("jlong@pivotal.io");
        todo.setDescription("Prepare Cloud Native Presentation");

        messageBoardService.save(todo);

        todo = new Todo();
        todo.setOwner("rwinch@pivotal.io");
        todo.setDescription("Finish Spring Security Reactive.");

        messageBoardService.save(todo);


    }
}
