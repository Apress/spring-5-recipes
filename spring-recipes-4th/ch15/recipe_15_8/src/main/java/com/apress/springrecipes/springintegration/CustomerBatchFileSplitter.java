package com.apress.springrecipes.springintegration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;

import org.springframework.integration.annotation.Splitter;


public class CustomerBatchFileSplitter {

    @Splitter
    public Collection<String> splitAFile(File file) throws IOException {
        System.out.printf("Reading %s....%n", file.getAbsolutePath());
        return Files.readAllLines(file.toPath());
    }
}
