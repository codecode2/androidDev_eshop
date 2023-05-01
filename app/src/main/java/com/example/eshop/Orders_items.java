package com.example.eshop;

import com.google.firebase.firestore.DocumentReference;

public class Orders_items {

    private DocumentReference customer_id;
    private int id_order_items;
    private int price;
    private DocumentReference product_id;
    private int quantity ;


    public DocumentReference getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(DocumentReference customer_id) {
        this.customer_id = customer_id;
    }

    public DocumentReference getProduct_id() {
        return product_id;
    }

    public void setProduct_id(DocumentReference product_id) {
        this.product_id = product_id;
    }

    public int getId_order_items() {
        return id_order_items;
    }

    public void setId_order_items(int id_order_items) {
        this.id_order_items = id_order_items;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
