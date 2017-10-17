package com.apress.springrecipes.shop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.springframework.beans.factory.BeanNameAware;

public class Cashier implements BeanNameAware {

    private String fileName;
    private String path;
    private BufferedWriter writer;

    public void setPath(String path) {
        this.path = path;
    }

    public void openFile() throws IOException {
        File targetDir = new File(path);
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }
        File checkoutFile = new File(path, fileName + ".txt");
        if (!checkoutFile.exists()) {
            checkoutFile.createNewFile();
        }
        writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(checkoutFile, true)));
    }

    public void checkout(ShoppingCart cart) throws IOException {
        writer.write(new Date() + "\t" + cart.getItems() + "\r\n");
        writer.flush();
    }

    public void closeFile() throws IOException {
        writer.close();
    }

    @Override
    public void setBeanName(String name) {
        this.fileName=name;
    }
}
