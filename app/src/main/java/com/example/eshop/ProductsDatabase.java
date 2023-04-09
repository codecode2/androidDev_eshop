package com.example.eshop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "products")
public class ProductsDatabase {
    @ColumnInfo(name="products_id")
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "products_name")
    private String products_name;

    @ColumnInfo (name = "product_description")
    private String product_description;

    @ColumnInfo (name = "price")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
