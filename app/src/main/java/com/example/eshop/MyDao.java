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
    public void insertProduct(ProductsDatabase productsdatabase);
    @Insert
    public void insertSupplier(SupplierDatabase supplierDatabase);

    @Query("select * from products")
    public List<ProductsDatabase> getProducts();

    @Query("select * from categories")
    public List<CategoriesDatabase> getCategories();

    @Delete
    public void deleteProduct(ProductsDatabase ProductsDatabase);

    @Update
    public void updateProducts(ProductsDatabase ProductsDatabase);

    @Update
    public void updateSupplier(SupplierDatabase supplierDatabase);

    @Delete
    public void deleteSupplier(SupplierDatabase supplierDatabase);


    @Insert
    public void insertCategory(CategoriesDatabase categoriesDatabase);

   @Update
    public void updateCategory(CategoriesDatabase categoriesDatabase);

   @Delete
   public void deleteCategory(CategoriesDatabase categoriesDatabase);


   @Insert
   public void insertSupplies(SuppliesDatabase categoriesDatabase);

    @Update
    public void updateSupplies(SuppliesDatabase categoriesDatabase);


    @Delete
    public void deleteSupplies(SuppliesDatabase categoriesDatabase);









}
