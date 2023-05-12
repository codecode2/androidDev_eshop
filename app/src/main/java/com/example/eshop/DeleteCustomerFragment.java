package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;


/**
 * A simple {@link Fragment} subclass.
 */


public class DeleteCustomerFragment extends Fragment {
    EditText id_customer, customer_username,customer_address,customer_email,customer_phone ;
    Button submit_button;

    public DeleteCustomerFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.delete_customer, container, false);

        WelcomePageActivity activity = (WelcomePageActivity) getActivity();

         id_customer = view.findViewById(R.id.customer_id);
         submit_button=view.findViewById(R.id.deleteCustomerSubmitButton3);




        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int customer_id = 0;
                try {
                    customer_id = Integer.parseInt(id_customer.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }




                try {

                    Customers customer = new Customers();
                    customer.setCustomer_id(customer_id);



                    WelcomePageActivity.db_firestore.collection("Customers").document(" "+customer_id).
                            delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    activity.createNotifications("Deletion Success","The record inserted Deleted");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    activity.createNotifications("Deletion Failed","The record is not Deleted");
                                }
                            });


                } catch (Exception e) {
                    String message = e.getMessage();

                }
                id_customer.setText("");

            }
        });
        return view;
    }
}
