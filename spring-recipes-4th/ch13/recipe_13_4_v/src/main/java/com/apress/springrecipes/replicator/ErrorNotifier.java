package com.apress.springrecipes.replicator;

public interface ErrorNotifier {

    void notifyCopyError(String srcDir, String destDir, String filename);
}