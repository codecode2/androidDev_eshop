package com.example.eshop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;




@Entity (tableName = "ProductCategories",
        primaryKeys = {"id"},
        foreignKeys = {
                @ForeignKey(entity = ProductsDatabase.class,
                        parentColumns = "id_categories",
                        childColumns = "id_product_categories",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)})

public class ProductCategoriesDatabase {

    @PrimaryKey
    private int id;

    @ColumnInfo( name = " id_categories_foreign")
    private int  product_categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_categories() {
        return product_categories;
    }

    public void setProduct_categories(int product_categories) {
        this.product_categories = product_categories;
    }
}
