package com.apress.springrecipes.cloud;

import java.util.List;

/**
 * Created by marten on 06-10-14.
 */
public interface ContactRepository {

    List<Contact> findAll();
    void save(Contact c);

}
