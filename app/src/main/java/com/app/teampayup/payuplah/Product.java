package com.app.teampayup.payuplah;

import java.util.Date;

public class Product {
    int itemID;
    double price;
    String date;
    String description;
    String category;
    String productName;
    String type; //for differentiating if expense("e") or income("i")

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

    public String getType(){return type;}

    public void setType(String type){this.type = type;}

    public Product() {
    }

    public Product(double price, String date, String description, String category,String ProductName, String type) {
        this.price = price;
        this.date = date;
        this.description = description;
        this.category = category;
        this.productName = ProductName;
        this.type = type;
    }


    public Product(int itemID, double price, String date, String description, String category,String ProductName, String type) {
        this.itemID = itemID;
        this.price = price;
        this.date = date;
        this.description = description;
        this.category = category;
        this.productName = ProductName;
        this.type = type;
    }
}
