package com.example.eshop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;




@Entity (tableName = "supplies",
        primaryKeys = {"id"},
        foreignKeys = {
                @ForeignKey(entity = SupplierDatabase.class,
                        parentColumns = "supplier_id",
                        childColumns = "id_supplier_foreign",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),

                @ForeignKey(entity = ProductsDatabase.class,
                        parentColumns = "products_id",
                        childColumns = "id_product_foreign",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE


                )})

public class SuppliesDatabase {
    @ColumnInfo(name="id_supplies")
    @PrimaryKey
    private int id;

    @ColumnInfo( name = "id_supplier_foreign")
    private int  id_sup;

    @ColumnInfo( name = "id_product_foreign")
    private int  id_prod;



}
