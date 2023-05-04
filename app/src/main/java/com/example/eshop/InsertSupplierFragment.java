package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */


public class InsertSupplierFragment extends Fragment {
    EditText id, name, nickname, address,phone;
    Button submit_button;

    public InsertSupplierFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insert_supplier, container, false);
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
                    WelcomePageActivity.myAppDatabase.myDao().insertSupplier(supplier);
                    Toast.makeText(getActivity(),"Record added.",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                id.setText("");
                name.setText("");
                nickname.setText("");
                address.setText("");
            }
        });
        return view;
    }
}
