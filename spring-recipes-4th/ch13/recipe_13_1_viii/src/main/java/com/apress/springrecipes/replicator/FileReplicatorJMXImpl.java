package com.apress.springrecipes.replicator;

import java.io.File;
import java.io.IOException;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(description = "File replicator")
public class FileReplicatorJMXImpl implements FileReplicator {
        
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;
    
    @ManagedAttribute(description = "Get source directory")
    public String getSrcDir() { 
	return srcDir;
    }
    @ManagedAttribute(description = "Get destination directory")
    public String getDestDir() { 
	return destDir;	
    }
    
    public FileCopier getFileCopier() { 
	return fileCopier;
    }

    @ManagedAttribute(description = "Set source directory")
    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

    @ManagedAttribute(description = "Set destination directory")
    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public void setFileCopier(FileCopier fileCopier) {
	this.fileCopier = fileCopier;
    }

 
    @ManagedOperation(description = "Replicate files")
    public synchronized void replicate() throws IOException {
        File[] files = new File(srcDir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileCopier.copyFile(srcDir, destDir, file.getName());
            }
        }
    }
}
