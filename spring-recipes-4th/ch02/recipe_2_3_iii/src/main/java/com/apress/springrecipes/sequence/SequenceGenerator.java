package com.apress.springrecipes.sequence;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

public class SequenceGenerator {
    @Autowired
    private PrefixGenerator[] prefixGenerators;
    private String suffix;
    private int initial;
    private AtomicInteger counter = new AtomicInteger();

    public SequenceGenerator() {
    }

    public SequenceGenerator(PrefixGenerator[] prefixGenerators, String suffix, int initial) {
        this.prefixGenerators = prefixGenerators;
        this.suffix = suffix;
        this.initial = initial;
    }

    public void setPrefixGenerator(PrefixGenerator[] prefixGenerators) {
        this.prefixGenerators = prefixGenerators;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {
        StringBuilder builder = new StringBuilder();
        for (PrefixGenerator prefix : prefixGenerators) {
            builder.append(prefix.getPrefix());
            builder.append("-");
        }
        builder.append(initial).append(counter.getAndIncrement()).append(suffix);
        return builder.toString();
    }
}
