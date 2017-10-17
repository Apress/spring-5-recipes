package com.apress.springrecipes.nosql;

/**
 * Created by marten on 10-10-14.
 */
public interface StarwarsService {

    Planet save(Planet planet);
    Character save(Character charachter);
    void printAll();

}
