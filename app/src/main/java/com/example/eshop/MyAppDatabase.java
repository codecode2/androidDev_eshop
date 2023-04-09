package com.example.eshop;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ProductsDatabase.class,CategoriesDatabase.class,SupplierDatabase.class,SuppliesDatabase.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
