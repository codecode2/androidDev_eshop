package com.example.eshop;

public class ProductAndSuppliers {

    private int products_id;
    private String products_name;
    private String product_description;
    private double price;
    private int supplier_id;
    private String supplier_name;
    private String supplier_nickname;

    private String category_of_product;

    private int quantity_product;


    public void setQuantity_product(int quantity_product) {
        this.quantity_product = quantity_product;
    }

    public int getQuantity_product() {
        return quantity_product;
    }

    public String getCategory_of_product() {
        return category_of_product;
    }

    public void setCategory_of_product(String category_of_product) {
        this.category_of_product = category_of_product;
    }

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
    }

    public String getProducts_name() {
        return products_name;
    }

    public void setProducts_name(String products_name) {
        this.products_name = products_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_nickname() {
        return supplier_nickname;
    }

    public void setSupplier_nickname(String supplier_nickname) {
        this.supplier_nickname = supplier_nickname;
    }
}
