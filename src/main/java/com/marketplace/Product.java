package com.marketplace;

public class Product {
    private int id;
    private String name;
    private int price;

    public Product() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return id + " " + name + " " + price;
    }
}
