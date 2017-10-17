package com.apress.springrecipes.nosql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by marten on 10-10-14.
 */
public class Character {

    private Long id;
    private String name;
    private Planet location;
    private final List<Character> friends = new ArrayList<>();
    private Character apprentice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Character> getFriends() {
        return Collections.unmodifiableList(friends);
    }

    public void addFriend(Character friend) {
        friends.add(friend);
    }
}
