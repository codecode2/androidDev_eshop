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


    @Query("select * from products where products_name= :name")
    public List<ProductsDatabase> getProductsWithVarName(String name);

    @Query("select * from products where category_of_product= :category")
    public List<ProductsDatabase> getProductsWithVarCategory(String category);

    @Query("select * from products where category_of_product= :category and products_name= :name")
    public List<ProductsDatabase> getProductsWithTogether(String category,String name);
    @Query("select * from categories")
    public List<CategoriesDatabase> getCategories();


    @Query("Select * from supplier")
    public List<SupplierDatabase> getSuppliers();


    @Query("Select * from supplier where supplier_name = :name or supplier_nickname=:name")
    public List<SupplierDatabase>  getSuppliersWithVarName(String name);

    @Query("Select * from supplier where supplier_id=:id_var")
    public List<SupplierDatabase> getSuppliersWithVarID(int id_var);


    @Query("select * from products where products_id = (select id_supplies from supplies)")
    public List<ProductsDatabase> getSuppliesProducts();

    @Query("select * from products where products_id = (select id_supplies from supplies)")
    public List<ProductsDatabase> getSuppliesProducts2();


    @Query("Select * from supplies")
    public List<SuppliesDatabase> getSupplies();

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
   public void insertSupplies(SuppliesDatabase suppliesDatabase);

    @Update
    public void updateSupplies(SuppliesDatabase suppliesDatabase);


    @Delete
    public void deleteSupplies(SuppliesDatabase suppliesDatabase );









}
