package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;


/**
 * A simple {@link Fragment} subclass.
 */


public class ModifySupplierFragment extends Fragment {
    EditText id, name, nickname, address,phone;
    Button submit_button;

    public ModifySupplierFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.modify_supplier, container, false);
        WelcomePageActivity activity = (WelcomePageActivity) getActivity();
        id = view.findViewById(R.id.supplier_id);
        name = view.findViewById(R.id.supplier_name);
        nickname = view.findViewById(R.id.supplier_nickname);
        address = view.findViewById(R.id.supplier_address);
        phone=view.findViewById(R.id.supplier_phone);
        submit_button = view.findViewById(R.id.insertProductSubmitButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_supplier_id = 0;
                try {
                    Var_supplier_id = Integer.parseInt(id.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_supplier_name = name.getText().toString();
                String Var_supplier_nickname = nickname.getText().toString();
                String Var_supplier_address = address.getText().toString();
                String Var_supplier_phone = phone.getText().toString();






                try {
                    SupplierDatabase supplier = new SupplierDatabase();
                    supplier.setId(Var_supplier_id);
                    supplier.setSupplier_name(Var_supplier_name);
                    supplier.setSupplier_nickname(Var_supplier_nickname);
                    supplier.setAddress(Var_supplier_address);
                    supplier.setPhone(Var_supplier_phone);

                    if(Var_supplier_name.equals("") || (Var_supplier_nickname.equals("")|| (Var_supplier_phone).equals("")))

                    {
                        activity.createNotifications("Modified Failed","Empty regions");


                    }else {
                        WelcomePageActivity.myAppDatabase.myDao().updateSupplier(supplier);

                        supplierfirebase supplierfire = new supplierfirebase();
                        supplierfire.setId_supplier(Var_supplier_id);
                        supplierfire.setName_supplier(Var_supplier_name);
                        supplierfire.setName_nickname(Var_supplier_nickname);
                        supplierfire.setAddress(Var_supplier_address);
                        supplierfire.setPhone(Var_supplier_phone);

                        WelcomePageActivity.db_firestore.collection("supplierfirebase").document(" " + Var_supplier_id).
                                set(supplierfire).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        activity.createNotifications("Modified Success", "The record modified succesfully");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });


                    }

                } catch (Exception e) {
                    String message = e.getMessage();
                    activity.createNotifications("Modified Failed","The record is not modified");
                }
                id.setText("");
                name.setText("");
                nickname.setText("");
                address.setText("");
                phone.setText("");
            }
        });
        return view;
    }
}
