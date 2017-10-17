package com.apress.springrecipes.bookshop;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class BookShopCashier implements Cashier {
    private BookShop bookShop;

    public void setBookShop(BookShop bookShop) {
        this.bookShop = bookShop;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void checkout(List<String> isbns, String username) {
        for (String isbn : isbns) {
            bookShop.purchase(isbn, username);
        }
    }
}
