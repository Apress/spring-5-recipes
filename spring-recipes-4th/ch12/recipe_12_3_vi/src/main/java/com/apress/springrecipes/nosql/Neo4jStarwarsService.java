package com.apress.springrecipes.nosql;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Neo4jStarwarsService implements StarwarsService {

    private final PlanetRepository planetRepository;
    private final CharacterRepository characterRepository;

    Neo4jStarwarsService(PlanetRepository planetRepository, CharacterRepository characterRepository) {
        this.planetRepository=planetRepository;
        this.characterRepository=characterRepository;
    }

    @Override
    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    @Override
    public Character save(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public void printAll() {
        planetRepository.findAll().forEach(System.out::println);
        characterRepository.findAll().forEach(System.out::println);
    }

    @PreDestroy
    public void cleanUp() {
        // Clean up when shutdown
        characterRepository.deleteAll();
        planetRepository.deleteAll();
    }
}
