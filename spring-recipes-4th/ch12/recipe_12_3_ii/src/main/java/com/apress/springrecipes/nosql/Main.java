package com.apress.springrecipes.nosql;

import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.nio.file.Paths;

import static com.apress.springrecipes.nosql.Main.RelationshipTypes.*;

public class Main {

    public enum RelationshipTypes implements RelationshipType {FRIENDS_WITH, MASTER_OF, SIBLING, LOCATION}

    public static void main(String[] args) {
        final String DB_PATH = System.getProperty("user.home") + "/friends";
        final GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(Paths.get(DB_PATH).toFile());
        final Label character = Label.label("character");
        final Label planet = Label.label("planet");

        try (Transaction tx1 = db.beginTx()) {


            // Planets
            Node dagobah = db.createNode(planet);
            dagobah.setProperty("name", "Dagobah");

            Node tatooine = db.createNode(planet);
            tatooine.setProperty("name", "Tatooine");

            Node alderaan = db.createNode(planet);
            alderaan.setProperty("name", "Alderaan");

            // Characters
            Node yoda = db.createNode(character);
            yoda.setProperty("name", "Yoda");

            Node luke = db.createNode(character);
            luke.setProperty("name", "Luke Skywalker");

            Node leia = db.createNode(character);
            leia.setProperty("name", "Leia Organa");

            Node han = db.createNode(character);
            han.setProperty("name", "Han Solo");

            // Relations
            yoda.createRelationshipTo(luke, MASTER_OF);
            yoda.createRelationshipTo(dagobah, LOCATION);
            luke.createRelationshipTo(leia, SIBLING);
            luke.createRelationshipTo(tatooine, LOCATION);
            luke.createRelationshipTo(han, FRIENDS_WITH);
            leia.createRelationshipTo(han, FRIENDS_WITH);
            leia.createRelationshipTo(alderaan, LOCATION);

            tx1.success();
        }

        Result result = db.execute("MATCH (n) RETURN n.name as name");
        result.stream()
                .flatMap(m -> m.entrySet().stream())
                .map(row -> row.getKey() + " : " + row.getValue() + ";")
                .forEach(System.out::println);

        db.shutdown();
    }
}
