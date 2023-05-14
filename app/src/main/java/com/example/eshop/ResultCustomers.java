package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ResultCustomers extends Fragment implements View.OnClickListener {
    TextView myTextView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list;
    private RecyclerAdapter adapter;

    List<String> product_list = new ArrayList<>();




    Spinner spinner;
    Button search_button;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultCustomers() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);



        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_result, container, false);

        getParentFragmentManager().setFragmentResultListener("DataFromProduct", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String  selected_item = result.getString("df1");
                String  text_written = result.getString("df2");

                String parts[]= selected_item.split(" ");

                if (selected_item.equals("All Customers") && text_written.equals("")) {



                    CollectionReference collectionReference = WelcomePageActivity.db_firestore.collection("Customers");
                    collectionReference.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                        Customers cust = documentSnapshot.toObject(Customers.class);
                                        int id_customer = cust.getCustomer_id();
                                        String name = cust.getName();
                                        String email= cust.getEmail();
                                        String address= cust.getAddress();
                                        String phone =cust.getPhone();

                                        product_list.add("Customer id: "+id_customer
                                        +"\nUsername: "+name
                                        +"\nEmail: "+ email+
                                                "\nAddress: "+address+
                                        "\nPhone: "+phone);
                                    }
                                    recyclerView = view.findViewById(R.id.recyclerview);
                                    layoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    //list = Arrays.asList(getResources().getStringArray(R.array.all_strings_username));

                                    adapter = new RecyclerAdapter(product_list);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setAdapter(adapter);




                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle the failure of retrieving the items from the "Order_items" collection
                                }
                            });

                }else
                {


                    CollectionReference collectionReference = WelcomePageActivity.db_firestore.collection("Customers");
                    collectionReference.whereEqualTo("name",text_written).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                        Customers cust = documentSnapshot.toObject(Customers.class);
                                        int id_customer = cust.getCustomer_id();
                                        String name = cust.getName();
                                        String email= cust.getEmail();
                                        String address= cust.getAddress();
                                        String phone =cust.getPhone();

                                        product_list.add("Customer id: "+id_customer
                                                +"\nUsername: "+name
                                                +"\nEmail: "+ email+
                                                "\nAddress: "+address+
                                                "\nPhone: "+phone);
                                    }
                                    recyclerView = view.findViewById(R.id.recyclerview);
                                    layoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(layoutManager);
                                    //list = Arrays.asList(getResources().getStringArray(R.array.all_strings_username));

                                    adapter = new RecyclerAdapter(product_list);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setAdapter(adapter);




                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle the failure of retrieving the items from the "Order_items" collection
                                }
                            });









                }

















            }

        });



        return view;

    }

    @Override
    public void onClick(View v) {



    }
}



