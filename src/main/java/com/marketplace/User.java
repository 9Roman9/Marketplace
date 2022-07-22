package com.marketplace;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int amountOfMoney;

    private List<Product> products = new ArrayList<>();

    public User() {}

    public int getId() {
        return id;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
    public void addProducts(Product product) {
        products.add(product);
    }

    @Override
    public String toString(){
        return id + " " + firstName + " " + lastName + " " + amountOfMoney;
    }
}
