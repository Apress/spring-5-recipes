package com.apress.springrecipes.replicator;

import java.io.File;
import java.io.IOException;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.FileCopyUtils;

 
@ManagedResource(
    objectName = "bean:name=fileCopier,type=FileCopierJMXImpl",
    description = "File Copier")
public class FileCopierJMXImpl implements FileCopier {

    @ManagedOperation(description = "Copy file from source directory to destination directory")
    @ManagedOperationParameters( {
	    @ManagedOperationParameter(name = "srcDir", description = "Source directory"),				       
	    @ManagedOperationParameter(name = "destDir", description = "Destination directory"),
	    @ManagedOperationParameter(name = "filename", description = "File to copy") })
    public void copyFile(String srcDir, String destDir, String filename)
            throws IOException {
        File srcFile = new File(srcDir, filename);
        File destFile = new File(destDir, filename);
        FileCopyUtils.copy(srcFile, destFile);
    }
}
