package com.marketplace;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigFile.class);
        Menu menu = context.getBean("menuBean",Menu.class);
        menu.setContext();
        menu.execute();
    }
}