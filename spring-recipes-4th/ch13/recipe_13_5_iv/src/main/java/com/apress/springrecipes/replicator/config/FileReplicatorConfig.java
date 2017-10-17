package com.apress.springrecipes.replicator.config;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.springrecipes.replicator.FileCopier;
import com.apress.springrecipes.replicator.FileCopierImpl;
import com.apress.springrecipes.replicator.FileReplicator;
import com.apress.springrecipes.replicator.FileReplicatorImpl;


@Configuration
public class FileReplicatorConfig {

    @Value("#{systemProperties['user.home']}/docs")
    private String srcDir;
    @Value("#{systemProperties['user.home']}/docs_backup")
    private String destDir;

    @Bean
    public FileCopier fileCopier() {
        FileCopier fCop = new FileCopierImpl();
        return fCop;
    }

    @Bean
    public FileReplicator documentReplicator() {
        FileReplicator fRep = new FileReplicatorImpl();
        fRep.setSrcDir(srcDir);
        fRep.setDestDir(destDir);
        fRep.setFileCopier(fileCopier());
        return fRep;
    }

    @PostConstruct
    public void verifyDirectoriesExist() {
        File src = new File(srcDir);
        File dest = new File(destDir);
        if (!src.exists())
            src.mkdirs();
        if (!dest.exists())
            dest.mkdirs();
    }
}
