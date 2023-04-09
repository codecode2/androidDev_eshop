package com.example.eshop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;




@Entity (tableName = "supplies",
        primaryKeys = {"id_supplies"},
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
    private int id;

    @ColumnInfo( name = "id_supplier_foreign")
    private int  id_sup;

    @ColumnInfo( name = "id_product_foreign")
    private int  id_prod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sup() {
        return id_sup;
    }

    public void setId_sup(int id_sup) {
        this.id_sup = id_sup;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }
}
