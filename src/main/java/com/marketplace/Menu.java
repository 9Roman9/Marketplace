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

    public void execute(){
        label:
        while (true){
            System.out.println("\nPlease, enter one of the next commands:\naddUser\n" +
                    "addProduct\ndeleteUser\ndeleteProduct\nbuyProduct\n" +
                    "displayListOfAllUsers\ndisplayListOfAllProducts\n" +
                    "showUsersByProductId\nshowProductsByUserId\nexit");
            Scanner scanner = new Scanner(System.in);
            String keyword = scanner.next();
            switch (keyword) {
                case "exit":
                    break label;
                case "addUser":
                    checkAddUser(scanner);
                    break;
                case "addProduct":
                    checkAddProduct(scanner);
                    break;
                case "displayListOfAllUsers":
                    displayListOfAllUsers();
                    break;
                case "displayListOfAllProducts":
                    displayListOfAllProducts();
                    break;
                case "buyProduct":
                    checkBuyProduct(scanner);
                    break;
                case "deleteUser":
                    checkDeleteUser(scanner);
                    break;
                case "deleteProduct":
                    checkDeleteProduct(scanner);
                    break;
                case "showProductsByUserId":
                    checkShowProductsByUserId(scanner);
                    break;
                case "showUsersByProductId":
                    checkShowUsersByProductId(scanner);
                    break;
                default:
                    System.err.println("You entered wrong command. Please, try again");
                    break;
            }
        }
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

    private void checkAddProduct(Scanner scanner){
        System.out.println("\nPlease, enter name of product:");
        String name;
        while (true){
            name = scanner.next();
            if (name.equals("")) System.err.println("The name is empty. Please, try again.");
            else break;
        }
        System.out.println("Please, enter price of product:");
        int priceInteger;
        while (true){
            try {
                String price = scanner.next();
                priceInteger = Integer.parseInt(price);
            } catch (NumberFormatException e) {
                System.err.println("The price is not integer. Please, try again.");
                continue;
            }
            break;
        }
        addProduct(name,priceInteger);
    }

    private void addProduct(String name, int price){
        Product product = context.getBean("productBean",Product.class);
        product.setId(productsArchive.size()+1);
        product.setName(name);
        product.setPrice(price);
        products.add(product);
        productsArchive.add(product);
    }

    private void checkBuyProduct(Scanner scanner){
        System.out.println("\nPlease, enter id of user:");
        int idUserInteger;
        while (true){
            try {
                String idUser = scanner.next();
                idUserInteger = Integer.parseInt(idUser);
            } catch (NumberFormatException e) {
                System.err.println("The id is not integer. Please, try again.");
                continue;
            }
            break;
        }
        System.out.println("Please, enter id of product:");
        int idProductInteger;
        while (true){
            try {
                String idProduct = scanner.next();
                idProductInteger = Integer.parseInt(idProduct);
            } catch (NumberFormatException e) {
                System.err.println("The id is not integer. Please, try again.");
                continue;
            }
            break;
        }
        buyProduct(idUserInteger,idProductInteger);
    }

    private void buyProduct(int idUser, int idProduct){
        if (users.isEmpty()){
            System.err.println("There are no users in marketplace. Please, add new users");
        } else if (products.isEmpty()) {
            System.err.println("There are no products in marketplace. Please, add new products");
        }

        User user = context.getBean("userBean",User.class);
        Product product = context.getBean("productBean",Product.class);
        boolean foundUser = false;
        boolean foundProduct = false;

        for (var u : users) if (u.getId()==idUser) {
            user = u;
            foundUser = true;
            break;
        }
        if (!foundUser) {
            System.err.println("There is no user with such id. Please, try again.");
            return;
        }
        for (var p : products) if (p.getId()==idProduct) {
            product = p;
            foundProduct = true;
            break;
        }
        if (!foundProduct) {
            System.err.println("There is no product with such id. Please, try again.");
            return;
        }
        if (product.getPrice()>user.getAmountOfMoney()) {
            System.err.println("The price of product is higher than amount of money of user." +
                    " Please, try again");
        } else {
            System.out.println("The purchase was successful!");
            user.setAmountOfMoney(user.getAmountOfMoney()- product.getPrice());
            user.addProducts(product);
        }
    }

    private void checkShowUsersByProductId(Scanner scanner){
        System.out.println("\nPlease, enter id of product:");
        int idProductInteger;
        while (true){
            try {
                String idProduct = scanner.next();
                idProductInteger = Integer.parseInt(idProduct);
            } catch (NumberFormatException e) {
                System.err.println("The id is not integer. Please, try again.");
                continue;
            }
            break;
        }
        showUsersByProductId(idProductInteger);
    }

    private void showUsersByProductId(int id){
        System.out.println();
        for (var p: products){
            if (p.getId()==id){
                for (var u : users){
                    if (u.getProducts().contains(p)) System.out.println(u);
                }
                return;
            }
        }
        System.err.println("There is no product with such id. Please try again.");
    }

    private void checkShowProductsByUserId(Scanner scanner){
        System.out.println("\nPlease, enter id of user:");
        int idUserInteger;
        while (true){
            try {
                String idUser = scanner.next();
                idUserInteger = Integer.parseInt(idUser);
            } catch (NumberFormatException e) {
                System.err.println("The id is not integer. Please, try again.");
                continue;
            }
            break;
        }
        showProductsByUserId(idUserInteger);
    }

    private void showProductsByUserId(int id){
        System.out.println();
        for (var u: users){
            if (u.getId()==id){
                List<Product> productsOfUser = u.getProducts();
                for (var p : productsOfUser) System.out.println(p.toString());
                return;
            }
        }
        System.err.println("There is no user with such id. Please try again.");
    }

    private void checkDeleteUser(Scanner scanner){
        System.out.println("\nPlease, enter id of user:");
        int idUserInteger;
        while (true){
            try {
                String idUser = scanner.next();
                idUserInteger = Integer.parseInt(idUser);
            } catch (NumberFormatException e) {
                System.err.println("The id is not integer. Please, try again.");
                continue;
            }
            break;
        }
        deleteUser(idUserInteger);
    }

    private void deleteUser(int id){
        for (var u : users){
            if(u.getId()==id) {
                users.remove(u);
                return;
            }
        }
        System.err.println("There is no user with such id. Please try again.");
    }

    private void checkDeleteProduct(Scanner scanner){
        System.out.println("\nPlease, enter id of product:");
        int idProductInteger;
        while (true){
            try {
                String idProduct = scanner.next();
                idProductInteger = Integer.parseInt(idProduct);
            } catch (NumberFormatException e) {
                System.err.println("The id is not integer. Please, try again.");
                continue;
            }
            break;
        }
        deleteProduct(idProductInteger);
    }

    private void deleteProduct(int id) {
        for (var p : products) {
            if (p.getId()==id){
                for (var u : users) {
                    for (var pp : u.getProducts()){
                        if (pp.equals(p)) u.getProducts().remove(pp);
                        break;
                    }
                }
                products.remove(p);
                return;
            }
        }
        System.err.println("There is no product with such id. Please try again.");
    }

    private void displayListOfAllUsers(){
        System.out.println();
        for (var u : users) System.out.println(u.toString());
    }

    private void displayListOfAllProducts(){
        System.out.println();
        for (var p : products) System.out.println(p.toString());
    }
}
