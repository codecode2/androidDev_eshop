package com.example.eshop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.checkerframework.common.aliasing.qual.Unique;
import org.jetbrains.annotations.NotNull;

@Entity (tableName = "categories")
public class CategoriesDatabase {

    @ColumnInfo(name="id_category")
    @PrimaryKey
    private int id;


    @Unique
    @NotNull
    @ColumnInfo(name = "name_category")


    private String category_name;

    @ColumnInfo (name = "category_description")
    private String category_description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }
}
