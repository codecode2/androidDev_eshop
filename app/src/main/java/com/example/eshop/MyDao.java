package com.example.eshop;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addUser(ProductsDatabase ProductsDatabase);

    @Query("select * from products")
    public List<ProductsDatabase> getProducts();

    @Delete
    public void deleteUser(ProductsDatabase ProductsDatabase);

    @Update
    public void updateUser(ProductsDatabase ProductsDatabase);






}
