package com.apress.springrecipes.nosql;

public interface StarwarsRepository {

    Planet save(Planet planet);
    Character save(Character charachter);

}
