package com.apress.springrecipes.nosql;

public interface StarwarsService {

    Planet save(Planet planet);
    Character save(Character charachter);
    void printAll();

}
