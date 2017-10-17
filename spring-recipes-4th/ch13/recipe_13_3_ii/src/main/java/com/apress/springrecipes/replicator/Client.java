package com.apress.springrecipes.replicator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Client {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.apress.springrecipes.replicator.config");

        FileReplicator fileReplicatorProxy = context.getBean(FileReplicator.class);

        String srcDir = fileReplicatorProxy.getSrcDir();
        fileReplicatorProxy.setDestDir(srcDir + "_backup");
        fileReplicatorProxy.replicate();
    }
}
