package com.example.eshop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "supplier")
public class SupplierDatabase {
    @ColumnInfo(name="supplier_id")
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "supplier_name")
    private String supplier_name;

    @ColumnInfo (name = "supplier_nickname")
    private String supplier_nickname;

    @ColumnInfo (name = "address")
    private String address;


    @ColumnInfo (name = "phone")
    private String phone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_nickname() {
        return supplier_nickname;
    }

    public void setSupplier_nickname(String supplier_nickname) {
        this.supplier_nickname = supplier_nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
