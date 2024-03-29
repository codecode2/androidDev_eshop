package com.example.eshop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class welcomeFragment extends Fragment {

    TextView message1,message2,message3;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public welcomeFragment() {
        // Required empty public constructor
    }

    public static MenuUploadFragment newInstance(String param1, String param2) {
        MenuUploadFragment fragment = new MenuUploadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        View view = inflater.inflate(R.layout.welcome_page, container, false);

        message1 = view.findViewById(R.id.messageinfo);
        message2 = view.findViewById(R.id.messageinfo2);
        message3 = view.findViewById(R.id.messageinfo3);



        CollectionReference collectionReference = WelcomePageActivity.db_firestore.collection("Customers");
        CollectionReference collectionReference2 = WelcomePageActivity.db_firestore.collection("supplierfirebase");
        CollectionReference collectionReference3 = WelcomePageActivity.db_firestore.collection("productsfirestore");
        CollectionReference collectionReference4 = WelcomePageActivity.db_firestore.collection("categories");
        CollectionReference collectionReference5 = WelcomePageActivity.db_firestore.collection("Orders");

        Task<QuerySnapshot> task1 = collectionReference.get();
        Task<QuerySnapshot> task2 = collectionReference2.get();
        Task<QuerySnapshot> task3 = collectionReference3.get();
        Task<QuerySnapshot> task4 = collectionReference4.get();
        Task<QuerySnapshot> task5 = collectionReference5.get();

        Task<List<QuerySnapshot>> allTasks = Tasks.whenAllSuccess(task1, task2, task3, task4, task5);

        allTasks.addOnSuccessListener(new OnSuccessListener<List<QuerySnapshot>>() {
            @Override
            public void onSuccess(List<QuerySnapshot> querySnapshots) {
                int count1 = querySnapshots.get(0).size();
                int count2 = querySnapshots.get(1).size();
                int count3 = querySnapshots.get(2).size();
                int count4 = querySnapshots.get(3).size();
                int count5 = querySnapshots.get(4).size();


                message1.setText("                       Basic Info\n"+"Customers: " + count1 +
                        "         Categories: " + count4 +
                        "\nSuppliers: " + count2 +
                                "            Orders: " + count5+
                        "\nProducts: " + count3

                     );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                message1.setText("No internet connection");
            }
        });



        CollectionReference productsCollection = WelcomePageActivity.db_firestore.collection("productsfirestore");

        productsCollection.orderBy("quantity").limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    // Retrieve the first document (product) in the result
                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);

                    // Assuming your product document has fields like id_product, name_product, quantity
                    int idProd = documentSnapshot.getLong("id_product").intValue();
                    String nameProd = documentSnapshot.getString("name_product");
                    int prodQuantity = documentSnapshot.getLong("quantity").intValue();
                    String product_of_category = documentSnapshot.getString("product_of_category");
                    int price2 = documentSnapshot.getLong("product_price").intValue();

                    message2.append("                   Lowest Quantity Product\n"+
                            "Id: "+idProd +"\nName: "+nameProd+
                            "                   Quantity: "+prodQuantity+
                            "\nCategory: "+product_of_category+
                            "             price: "+price2+"€");

                   if (prodQuantity<=10)
                   {
                       Drawable drawable = getResources().getDrawable(R.drawable.rounded_low_quantity_product);

                       message2.setBackground(drawable);
                   }
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle failure in retrieving products
                // ...
            }
        });




        CollectionReference orders1 = WelcomePageActivity.db_firestore.collection("Orders");

        orders1.orderBy("date").limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    // Retrieve the first document (order) in the result
                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);

                    // Retrieve the date value from the document
                    Date date = documentSnapshot.getDate("date");
                    int cust_id= documentSnapshot.getLong("customerid").intValue();
                    // Format the date as needed
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String formattedDate = dateFormat.format(date);
                    int price = documentSnapshot.getLong("price").intValue();



                    CollectionReference collectionReference15 = WelcomePageActivity.db_firestore.collection("Customers");
                    collectionReference15.whereEqualTo("customer_id",cust_id).get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                                        Customers cust = documentSnapshot.toObject(Customers.class);

                                        String name = cust.getName();
                                        String email = cust.getEmail();

                                        message3.append(
                                                "                                   Last order"
                                                        + "\nCustomer id: " + cust_id+"                   Price:"+price+"€"
                                                        + "\nUsername: " + name
                                                        + "\nEmail: " + email +
                                                        "\nDate: " + formattedDate + "\n");

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
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle failure in retrieving orders
                // ...
            }
        });











        Bundle bundle = getArguments();


        return  view;    }

}

