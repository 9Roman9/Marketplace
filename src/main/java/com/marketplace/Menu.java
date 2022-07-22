package com.marketplace;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    AnnotationConfigApplicationContext context;
    private final List<User> users = new ArrayList<>();
    private final List<User> usersArchive = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Product> productsArchive = new ArrayList<>();

    public void setContext(){
        context = new AnnotationConfigApplicationContext(Config.class);
    }

    private void checkAddUser(Scanner scanner){
        System.out.println("\nPlease, enter first name of user:");
        String firstName;
        while (true){
            firstName = scanner.next();
            if (firstName.equals("")) System.out.println("The first name is empty. Please, try again.");
            else break;
        }
        System.out.println("Please, enter last name of user:");
        String lastName;
        while (true){
            lastName = scanner.next();
            if (lastName.equals("")) System.out.println("The last name is empty. Please, try again.");
            else break;
        }
        System.out.println("Please, enter amount of money of user:");
        String amountOfMoney;
        int amountOfMoneyInteger;
        while (true){
            try {
                amountOfMoney = scanner.next();
                amountOfMoneyInteger = Integer.parseInt(amountOfMoney);
            } catch (NumberFormatException e) {
                System.err.println("The amount of money is not integer. Please, try again.");
                continue;
            }
            break;
        }
        addUser(firstName,lastName,amountOfMoneyInteger);
    }

    private void addUser(String firstName, String lastName, int amountOfMoney){
        User user = context.getBean("userBean",User.class);
        user.setId(usersArchive.size()+1);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAmountOfMoney(amountOfMoney);
        users.add(user);
        usersArchive.add(user);
    }
}
