package com.apress.springrecipes.nosql;

import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StarwarsConfig.class);

        StarwarsService service = context.getBean(StarwarsService.class);

        // Planets
        Planet dagobah = new Planet();
        dagobah.setName("Dagobah");

        Planet alderaan = new Planet();
        alderaan.setName("Alderaan");

        Planet tatooine = new Planet();
        tatooine.setName("Tatooine");

        Stream.of(dagobah, alderaan, tatooine).forEach(service::save);

        // Characters
        Character han = new Character();
        han.setName("Han Solo");

        Character leia = new Character();
        leia.setName("Leia Organa");
        leia.setLocation(alderaan);
        leia.addFriend(han);

        Character luke = new Character();
        luke.setName("Luke Skywalker");
        luke.setLocation(tatooine);
        luke.addFriend(han);
        luke.addFriend(leia);

        Character yoda = new Character();
        yoda.setName("Yoda");
        yoda.setLocation(dagobah);
        yoda.setApprentice(luke);

        Stream.of(han, leia, luke, yoda).forEach(service::save);

        service.printAll();

        context.close();
    }
}
