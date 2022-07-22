package com.marketplace;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    AnnotationConfigApplicationContext context;
    private final List<User> users = new ArrayList<>();
    private final List<User> usersArchive = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Product> productsArchive = new ArrayList<>();

    public void setContext(){
        context = new AnnotationConfigApplicationContext(ConfigFile.class);
    }

}
