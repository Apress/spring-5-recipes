package com.apress.springrecipes.sequence;

public interface SequenceDao {

    Sequence getSequence(String sequenceId);

    int getNextValue(String sequenceId);
} 
