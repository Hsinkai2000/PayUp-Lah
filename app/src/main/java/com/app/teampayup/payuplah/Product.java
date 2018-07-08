package com.app.teampayup.payuplah;

import java.util.Date;

public class Product {
    int itemID;
    int profileID;
    double price;
    String date;
    String description;
    String category;
    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(int profileID, double price, String date, String description, String category,String ProductName) {
        this.profileID = profileID;
        this.price = price;
        this.date = date;
        this.description = description;
        this.category = category;
        this.productName = ProductName;
    }


    public Product(int itemID, int profileID, double price, String date, String description, String category,String ProductName) {
        this.itemID = itemID;
        this.profileID = profileID;
        this.price = price;
        this.date = date;
        this.description = description;
        this.category = category;
        this.productName = ProductName;
    }
}
