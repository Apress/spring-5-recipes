package com.apress.springrecipes.cloud;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MapBasedContactRepository implements ContactRepository {

    private final AtomicLong SEQUENCE = new AtomicLong();
    private Map<Long, Contact> contacts = new HashMap<>();

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(contacts.values());
    }

    @Override
    public void save(Contact c) {
        if (c.getId() <= 0) {
            c.setId(SEQUENCE.incrementAndGet());
        }
        contacts.put(c.getId(), c);
    }
}
