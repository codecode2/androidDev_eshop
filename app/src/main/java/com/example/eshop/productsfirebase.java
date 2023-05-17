package com.example.eshop;

public class productsfirebase {
    private int id_product;
    private String name_product;
    private String product_description;
    private String product_of_category;
    private int product_price;
    private int quantity;

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_of_category() {
        return product_of_category;
    }

    public void setProduct_of_category(String product_of_category) {
        this.product_of_category = product_of_category;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
