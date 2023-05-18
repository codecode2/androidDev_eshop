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


public class InsertSuppliesFragment extends Fragment {
    EditText id, product_id_sup, supplier_id_sup;
    Button submit_button;

    public InsertSuppliesFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insert_supplies, container, false);
        WelcomePageActivity activity = (WelcomePageActivity) getActivity();

        id = view.findViewById(R.id.supplies_id);
        product_id_sup = view.findViewById(R.id.product_id_supplies);
        supplier_id_sup = view.findViewById(R.id.supplier_id_supplies);

        submit_button = view.findViewById(R.id.insertSuppliesSubmitButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var_supply_id = 0;
                try {
                    var_supply_id = Integer.parseInt(id.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                int var_product_id = 0;
                try {
                    var_product_id = Integer.parseInt(product_id_sup.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                int var_supplier_id = 0;
                try {
                    var_supplier_id = Integer.parseInt(supplier_id_sup.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }


                try {
                    SuppliesDatabase supplies = new SuppliesDatabase();
                    supplies.setId(var_supply_id);
                    supplies.setId_prod(var_product_id);
                    supplies.setId_sup(var_supplier_id);

                    suppliesfirebase suppliesfire = new suppliesfirebase();
                    suppliesfire.setId_supplies(var_supply_id);
                    suppliesfire.setId_product(var_product_id);
                    suppliesfire.setId_supplier(var_supplier_id);

                    WelcomePageActivity.myAppDatabase.myDao().insertSupplies(supplies);


                    WelcomePageActivity.db_firestore.collection("suppliesfirestore").document(" "+var_supply_id).
                            set(suppliesfire).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    activity.createNotifications("Insertion Success","The record inserted succesfully");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });




                } catch (Exception e) {
                    String message = e.getMessage();
                    activity.createNotifications("Insertion Failed","The record is not inserted");
                }
                id.setText("");
                product_id_sup.setText("");
                supplier_id_sup.setText("");

            }
        });
        return view;
    }
}
