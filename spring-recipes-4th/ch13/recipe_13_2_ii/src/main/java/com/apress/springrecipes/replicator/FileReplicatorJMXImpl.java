package com.apress.springrecipes.replicator;

import java.io.File;
import java.io.IOException;

import javax.management.Notification;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

@ManagedResource(description = "File replicator")
public class FileReplicatorJMXImpl implements FileReplicator,NotificationPublisherAware {
        
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;
    private int sequenceNumber;
    private NotificationPublisher notificationPublisher;

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

    public int getSequenceNumber() { 
	return sequenceNumber;
    }

    public NotificationPublisher getNotificationPublisher() { 
	return notificationPublisher;
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

    public void setSequenceNumber(int sequenceNumber) {
	this.sequenceNumber = sequenceNumber;
    }

    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    } 

 
    @ManagedOperation(description = "Replicate files")
    public synchronized void replicate() throws IOException {
	notificationPublisher.sendNotification(new Notification("replication.start", this, sequenceNumber));

        File[] files = new File(srcDir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileCopier.copyFile(srcDir, destDir, file.getName());
            }
        }
	notificationPublisher.sendNotification(new Notification("replication.complete", this, sequenceNumber));
        sequenceNumber++;
    }

}
