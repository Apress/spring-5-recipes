package com.apress.springrecipes.nosql;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Character {

    @GraphId
    private Long id;
    private String name;

    @Relationship(type = "LOCATION")
    private Planet location;
    @Relationship(type="FRIENDS_WITH")
    private final Set<Character> friends = new HashSet<>();
    @Relationship(type="MASTER_OF")
    private Character apprentice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Planet getLocation() {
        return location;
    }

    public void setLocation(Planet location) {
        this.location = location;
    }

    public Character getApprentice() {
        return apprentice;
    }

    public void setApprentice(Character apprentice) {
        this.apprentice = apprentice;
    }

    public Set<Character> getFriends() {
        return Collections.unmodifiableSet(friends);
    }

    public void addFriend(Character friend) {
        friends.add(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", friends=" + friends.size() +
                ", apprentice=" + apprentice +
                '}';
    }
}
