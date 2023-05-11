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






    @Query("select * from products order by price ")
    public List<ProductsDatabase> OrderByPrice();

    @Query("select * from products order by products_id ")
    public List<ProductsDatabase> OrderById();

    @Query("select * from products order by products_name ")
    public List<ProductsDatabase> OrderByName();

    @Query("select * from products order by category_of_product ")
    public List<ProductsDatabase> OrderByNameCategory();

    @Query("select * from products order by quantity_product ")
    public List<ProductsDatabase> OrderByNameQuantity();







    @Query("SELECT * FROM products WHERE price > 10 ")
    public List<ProductsDatabase> getProductsPriceGT();

    @Query("select * from products where price <= 10")
    public List<ProductsDatabase> getProductsPriceLT();


    @Query("select * from products where quantity_product > 5")
    public List<ProductsDatabase> getProductsQuantityGT();

    @Query("select * from products where quantity_product <= 5")
    public List<ProductsDatabase> getProductsQuantityLT();



    @Query("select * from products where  quantity_product= (select MAX(quantity_product)from products)")
    public List<ProductsDatabase> getProductsQuantityMAX();


    @Query("select * from products where  quantity_product = (select MIN(quantity_product)from products)")
    public List<ProductsDatabase> getProductsQuantityMIN();


    @Query("select * from products where  price= (select MAX(price)from products)")
    public List<ProductsDatabase> getProductsPriceMAX();


    @Query("select * from products where  price= (select MIN(price)from products)")
    public List<ProductsDatabase> getProductsPriceMIN();






    @Query("select * from products where products_name= :name")
    public List<ProductsDatabase> getProductsWithVarName(String name);

    @Query("select * from products where products_name= :name order by products_id")
    public List<ProductsDatabase> getProductsWithVarNameOrderById(String name);

    @Query("select * from products where products_name= :name order by products_name")
    public List<ProductsDatabase> getProductsWithVarNameOrderByName(String name);

    @Query("select * from products where products_name= :name order by category_of_product")
    public List<ProductsDatabase> getProductsWithVarNameOrderByCategory(String name);

    @Query("select * from products where products_name= :name order by quantity_product")
    public List<ProductsDatabase> getProductsWithVarNameOrderByQuantity(String name);

    @Query("select * from products where products_name= :name order by price")
    public List<ProductsDatabase> getProductsWithVarNameOrderByPrice(String name);








    @Query("select * from products where category_of_product= :category")
    public List<ProductsDatabase> getProductsWithVarCategory(String category);

    @Query("select * from products where category_of_product= :category order by price")
    public List<ProductsDatabase> getProductsWithVarCategoryOrderByPrice(String category);

    @Query("select * from products where category_of_product= :category order by products_id")
    public List<ProductsDatabase> getProductsWithVarCategoryOrderById(String category);

    @Query("select * from products where category_of_product= :category order by products_name")
    public List<ProductsDatabase> getProductsWithVarCategoryOrderByName(String category);

    @Query("select * from products where category_of_product= :category order by category_of_product")
    public List<ProductsDatabase> getProductsWithVarCategoryOrderByCategory(String category);

    @Query("select * from products where category_of_product= :category order by quantity_product")
    public List<ProductsDatabase> getProductsWithVarCategoryOrderByQuantity(String category);








    @Query("select * from products where category_of_product= :category and products_name= :name")
    public List<ProductsDatabase> getProductsWithTogether(String category,String name);

    @Query("select * from products where category_of_product= :category and products_name= :name order by price")
    public List<ProductsDatabase> getProductsWithTogetherOrderByPrice(String category,String name);

    @Query("select * from products where category_of_product= :category and products_name= :name order by products_id")
    public List<ProductsDatabase> getProductsWithTogetherOrderById(String category,String name);

    @Query("select * from products where category_of_product= :category and products_name= :name order by products_name")
    public List<ProductsDatabase> getProductsWithTogetherOrderByName(String category,String name);

    @Query("select * from products where category_of_product= :category and products_name= :name order by category_of_product")
    public List<ProductsDatabase> getProductsWithTogetherOrderByCategory(String category,String name);

    @Query("select * from products where category_of_product= :category and products_name= :name order by quantity_product")
    public List<ProductsDatabase> getProductsWithTogetherOrderByQuantity(String category,String name);






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


    @Query("select products_id,products_name,product_description,category_of_product,quantity_product,price,supplier_id,supplier_name,supplier_nickname from products,supplier,supplies"+
            " where supplies.id_product_foreign=products_id and id_supplier_foreign=supplier_id ")
    public List<ProductAndSuppliers> getSuppliersAndProducts();

    @Query("select products_id,products_name,product_description,category_of_product,quantity_product,price,supplier_id,supplier_name,supplier_nickname " +
            "from products,supplier,supplies "+
            "where supplies.id_product_foreign=products_id and id_supplier_foreign=supplier_id and products_name= :product " +
            "UNION " +
            "select products_id,products_name,product_description,category_of_product,quantity_product,price,supplier_id,supplier_name,supplier_nickname from products,supplier,supplies "+
            " where supplies.id_product_foreign=products_id and id_supplier_foreign=supplier_id and supplier_name=:product "  +
             "UNION " +
            "select products_id,products_name,product_description,category_of_product,quantity_product,price,supplier_id,supplier_name,supplier_nickname " +
            "from products,supplier,supplies "+
            "where supplies.id_product_foreign=products_id and id_supplier_foreign=supplier_id and supplier_nickname= :product "        )
    public List<ProductAndSuppliers> getSuppliersAndProducts2(String product);

    @Query("select products_id,products_name,product_description,category_of_product,quantity_product,price,supplier_id,supplier_name,supplier_nickname from products,supplier,supplies"+
            " where supplies.id_product_foreign=products_id and id_supplier_foreign=supplier_id and id_supplier_foreign= :supplier ")
    public List<ProductAndSuppliers> getSuppliersAndProducts3(int supplier);

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
