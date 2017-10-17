package com.apress.springrecipes.nosql;

import java.util.Collections;

import javax.annotation.PreDestroy;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Neo4jStarwarsRepository implements StarwarsRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public Neo4jStarwarsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @SuppressWarnings("try")
    public Planet save(Planet planet) {

        Session session = sessionFactory.openSession();
        try ( Transaction tx = session.beginTransaction()) {
            session.save(planet);
            return planet;
        }
    }

    @Override
    @SuppressWarnings("try")
    public Character save(Character character) {

        Session session = sessionFactory.openSession();
        try (Transaction tx = session.beginTransaction()) {
            session.save(character);
            return character;
        }
    }

    @Override
    public void printAll() {

        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH (n) RETURN n.name as name", Collections.emptyMap(), true);
        result.forEach(m -> m.entrySet().stream()
                .map(row -> row.getKey() + " : " + row.getValue() + ";")
                .forEach(System.out::println));
    }

    @PreDestroy
    public void cleanUp() {
        // Clean up when shutdown
        Session session = sessionFactory.openSession();
        session.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r", null);
    }

}
