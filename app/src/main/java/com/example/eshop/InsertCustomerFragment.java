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


public class InsertCustomerFragment extends Fragment {
    EditText id_customer, customer_username,customer_address,customer_email,customer_phone ;
    Button submit_button;

    public InsertCustomerFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insert_customer, container, false);

        WelcomePageActivity activity = (WelcomePageActivity) getActivity();

         id_customer = view.findViewById(R.id.customer_id);
         customer_username= view.findViewById(R.id.username_name);
         customer_email=view.findViewById(R.id.email);
         customer_address=view.findViewById(R.id.customer_address);
         customer_phone=view.findViewById(R.id.customer_phone);
         submit_button= view.findViewById(R.id.insertCustomerSubmitButton);








        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int customer_id = 0;
                try {
                    customer_id = Integer.parseInt(id_customer.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String username = customer_username.getText().toString();
                String email = customer_email.getText().toString();
                String address = customer_address.getText().toString();
                String phone = customer_phone.getText().toString();


                try {




                    Customers customer = new Customers();
                    customer.setCustomer_id(customer_id);
                    customer.setName(username);
                    customer.setAddress(address);
                    customer.setEmail(email);
                    customer.setPhone(phone);


                    WelcomePageActivity.db_firestore.collection("Customers").document(" "+customer_id).
                            set(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    activity.createNotifications("Insertion Success","The record inserted succesfully");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    activity.createNotifications("Insertion Failed","The record is not inserted");
                                }
                            });

                } catch (Exception e) {
                    String message = e.getMessage();

                }
                id_customer.setText("");
                customer_username.setText("");
                customer_email.setText("");
                customer_address.setText("");
                customer_phone.setText("");
            }
        });
        return view;
    }
}
