package com.apress.springrecipes.bookshop;

import java.util.List;


public interface Cashier {

    void checkout(List<String> isbns, String username);
}
