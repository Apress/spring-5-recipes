package com.apress.springrecipes.replicator;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Client {

    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.apress.springrecipes.replicator.config");

        MBeanServerConnection mbeanServerConnection = context.getBean(MBeanServerConnection.class);
        ObjectName mbeanName = new ObjectName("bean:name=documentReplicator");
        mbeanServerConnection.addNotificationListener(mbeanName, new ReplicationNotificationListener(), null, null);
        String srcDir = (String) mbeanServerConnection.getAttribute(mbeanName, "SrcDir");
        mbeanServerConnection.setAttribute(mbeanName, new Attribute("DestDir", srcDir + "_backup"));
        mbeanServerConnection.invoke(mbeanName, "replicate", new Object[]{}, new String[]{});
    }
}
