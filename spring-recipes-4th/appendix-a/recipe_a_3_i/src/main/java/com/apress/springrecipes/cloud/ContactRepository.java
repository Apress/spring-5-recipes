package com.apress.springrecipes.cloud;

import java.util.List;

public interface ContactRepository {

    List<Contact> findAll();
    void save(Contact c);

}
