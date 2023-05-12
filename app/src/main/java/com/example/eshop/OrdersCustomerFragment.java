package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */


public class OrdersCustomerFragment extends Fragment {
    EditText  quantity,id ;
    Button submit_button;
    int count;
    ArrayAdapter<String> adapter,adapter2;
    Spinner spinnerCust , spinnerProd ;

    String [] partsUser, partsProduct;
    public OrdersCustomerFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.orders_customers, container, false);

        List<String> categories_results1 = new ArrayList<>();
        categories_results1.add("Choose a Product");
        WelcomePageActivity activity = (WelcomePageActivity) getActivity();

        CollectionReference collectionReference = WelcomePageActivity.db_firestore.collection("Customers");
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    Customers customersFirebase = documentSnapshot.toObject(Customers.class);
                    int id_customer = customersFirebase.getCustomer_id();
                    String name = customersFirebase.getName();
                    categories_results1.add("id: "+id_customer+" :Name: " +name);

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });


        spinnerCust = view.findViewById(R.id.id_customer_spinner);
         adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories_results1);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCust.setAdapter(adapter);

        spinnerCust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selection_User = spinnerCust.getSelectedItem().toString();

                partsUser = selection_User.split(":");



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

            Bundle bundle = getArguments();


        });



        List<String> categories_results2 = new ArrayList<>();
        CollectionReference collectionReference2 = WelcomePageActivity.db_firestore.collection("productsfirestore");
        categories_results2.add("Choose a Customer");
        collectionReference2.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    productsfirebase products = documentSnapshot.toObject(productsfirebase.class);
                    int idProd= products.getId_product();
                    String nameProd= products.getName_product();
                    int prodPrice = products.getProduct_price();
                    int quantProd=products.getQuantity();

                    categories_results2.add("id: "+idProd+" :Name: " +nameProd+" :Price: "+prodPrice+ " :Quantity: "+quantProd);

                }


             }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });





        spinnerProd = view.findViewById(R.id.id_product_customer_spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories_results2);
        adapter2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerProd.setAdapter(adapter2);

        spinnerProd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection_Product=spinnerProd.getSelectedItem().toString();
                partsProduct = selection_Product.split(":");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

            Bundle bundle = getArguments();


        });




        submit_button= view.findViewById(R.id.Submit_order);








          quantity= view.findViewById(R.id.product_quantity_customer);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<String> categories_results3 = new ArrayList<>();
                CollectionReference collectionReference3 = WelcomePageActivity.db_firestore.collection("Order_items");

                collectionReference3.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        count = queryDocumentSnapshots.size();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });







                try {

                    double pricing = Integer.parseInt(partsProduct[5].trim()) * Integer.parseInt(quantity.getText().toString());

                    Order_items orders_items = new Order_items();
                    orders_items.setCustomer_id(Integer.parseInt(partsUser[1].trim()));
                    orders_items.setPrice(pricing);
                    orders_items.setProduct_id(Integer.parseInt(partsProduct[1].trim()));
                    orders_items.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    orders_items.setTesting(partsProduct[7]);

                    count++;

                    WelcomePageActivity.db_firestore.collection("Order_items").document(" "+count).
                            set(orders_items).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                    Toast.makeText(getActivity(),"Record added.",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                quantity.setText("");
                spinnerCust.setSelection(0);
                spinnerProd.setSelection(0);
            }
        });
        return view;
    }
}
