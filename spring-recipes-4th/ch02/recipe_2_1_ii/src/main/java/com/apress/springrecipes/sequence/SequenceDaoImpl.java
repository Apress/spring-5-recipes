package com.apress.springrecipes.sequence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component("sequenceDao")
public class SequenceDaoImpl implements SequenceDao {

    private final Map<String, Sequence> sequences = new HashMap<>();
    private final Map<String, AtomicInteger> values = new HashMap<>();

    public SequenceDaoImpl() {
        sequences.put("IT", new Sequence("IT", "30", "A"));
        values.put("IT", new AtomicInteger(10000));
    }

    public Sequence getSequence(String sequenceId) {
        return sequences.get(sequenceId);
    }

    public int getNextValue(String sequenceId) {
        AtomicInteger value = values.get(sequenceId);
        return value.getAndIncrement();
    }
}
