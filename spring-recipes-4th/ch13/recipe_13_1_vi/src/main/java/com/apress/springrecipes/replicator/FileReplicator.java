package com.apress.springrecipes.replicator;

import java.io.IOException; 

public interface FileReplicator {

    String getSrcDir();
    void setSrcDir(String srcDir);

    String getDestDir();
    void setDestDir(String destDir);

    FileCopier getFileCopier();
    void setFileCopier(FileCopier fileCopier);
 
    void replicate() throws IOException;
}
