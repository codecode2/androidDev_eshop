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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.util.CustomClassMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ResultOrder extends Fragment implements View.OnClickListener {
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

    public ResultOrder() {
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

                if (selected_item.equals("All Orders") && text_written.equals("")) {

                                CollectionReference collectionReference = WelcomePageActivity.db_firestore.collection("Orders");
                                collectionReference.get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                                                int sumPrice = 0;
                                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                    Orders orders = documentSnapshot.toObject(Orders.class);
                                                    int id_customer =  orders.getCustomerid();
                                                    Date date = orders.getDate();
                                                    double price = orders.getPrice();
                                                    String items=orders.getItems();

                                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                                    String formattedDate = dateFormat.format(date);

                                                    CollectionReference collectionReference2 = WelcomePageActivity.db_firestore.collection("Customers");
                                                    collectionReference2.whereEqualTo("customer_id",id_customer).get()
                                                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                                                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                                        Customers customers = documentSnapshot.toObject(Customers.class);

                                                                        String cust_name= customers.getName();

                                                                        String mail=customers.getEmail();

                                                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                                                        String formattedDate = dateFormat.format(date);





                                                                        product_list.add("id: " + id_customer    + "\n"+
                                                                                "Customer: "+cust_name+"\nMail: "+mail+"\nitems: "+items+"\nPrice: "+price+"\nDate: "+date);
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
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle the failure of retrieving the items from the "Order_items" collection
                                            }
                                        });









                }else if (selected_item.equals("All Orders"))
                {





                    CollectionReference collectionReference = WelcomePageActivity.db_firestore.collection("Orders");
                    collectionReference.whereEqualTo("customerid",Integer.parseInt(text_written)).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                                    int sumPrice = 0;
                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                        Orders orders = documentSnapshot.toObject(Orders.class);
                                        int id_customer =  orders.getCustomerid();
                                        Date date = orders.getDate();
                                        double price = orders.getPrice();
                                        String items=orders.getItems();

                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                        String formattedDate = dateFormat.format(date);

                                        CollectionReference collectionReference2 = WelcomePageActivity.db_firestore.collection("Customers");
                                        collectionReference2.whereEqualTo("customer_id",id_customer).get()
                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                                                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                            Customers customers = documentSnapshot.toObject(Customers.class);

                                                            String cust_name= customers.getName();

                                                            String mail=customers.getEmail();

                                                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                                            String formattedDate = dateFormat.format(date);





                                                            product_list.add("id: " + id_customer    + "\n"+
                                                                    "Customer: "+cust_name+"\nMail: "+mail+"\nitems: "+items+"\nPrice: "+price+"\nDate: "+date);
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



