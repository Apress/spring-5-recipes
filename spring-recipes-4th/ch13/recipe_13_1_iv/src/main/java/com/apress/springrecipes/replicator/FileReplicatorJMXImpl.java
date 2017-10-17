package com.apress.springrecipes.replicator;

import java.io.File;
import java.io.IOException;


public class FileReplicatorJMXImpl implements FileReplicator {
        
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;
    
    public String getSrcDir() { 
	return srcDir;
    }

    public String getDestDir() { 
	return destDir;	
    }
    
    public FileCopier getFileCopier() { 
	return fileCopier;
    }

    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public void setFileCopier(FileCopier fileCopier) {
	this.fileCopier = fileCopier;
    }

 
    public synchronized void replicate() throws IOException {
        File[] files = new File(srcDir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileCopier.copyFile(srcDir, destDir, file.getName());
            }
        }
    }
}
