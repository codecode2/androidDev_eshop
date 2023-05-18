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


public class DeleteSupplierFragment extends Fragment {
    EditText id;
    Button submit_button;

    public DeleteSupplierFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.delete_supplier, container, false);
        id = view.findViewById(R.id.supplier_id);
        WelcomePageActivity activity = (WelcomePageActivity) getActivity();


        submit_button = view.findViewById(R.id.deleteProductSubmitButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int supplier_ids = 0;
                try {
                    supplier_ids = Integer.parseInt(id.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                try {
                    SupplierDatabase supplier = new SupplierDatabase();
                    supplier.setId(supplier_ids);

                    WelcomePageActivity.myAppDatabase.myDao().deleteSupplier(supplier);


                    supplierfirebase supplierfire = new supplierfirebase();
                    supplierfire.setId_supplier(supplier_ids);


                    WelcomePageActivity.db_firestore.collection("supplierfirebase").document(" "+supplier_ids).
                            delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    activity.createNotifications("Deletion Success","The record delete succesfully");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });




                } catch (Exception e) {
                    String message = e.getMessage();
                    activity.createNotifications("Deletion Failed","The record is not deleted");
                }
                id.setText("");

            }
        });
        return view;
    }
}
