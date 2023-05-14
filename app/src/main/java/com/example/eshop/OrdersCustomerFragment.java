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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */


public class OrdersCustomerFragment extends Fragment {
    EditText  quantity,id ;
    Button submit_button;

    String result="";
    int count;
    double totalPrice = 0.0;

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
                    int prod_id= Integer.parseInt(partsProduct[1].trim());
                    int quantityMinus = Integer.parseInt(partsProduct[7].trim()) - Integer.parseInt(quantity.getText().toString());

                    if (quantityMinus >= 0) {








                        count++;

                        WelcomePageActivity.db_firestore.collection("Order_items").document(" " + count)
                                .set(orders_items)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        activity.createNotifications("Insertion Success", "The record inserted successfully");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        activity.createNotifications("Insertion Failed", "The record is not inserted");
                                    }
                                });

                        Toast.makeText(getActivity(), "Record added.", Toast.LENGTH_LONG).show();

                        Orders sumorders = new Orders();
                        sumorders.setCustomerid(Integer.parseInt(partsUser[1].trim()));
                        sumorders.setDate(new Date());

                        int customerid = Integer.parseInt(partsUser[1].trim());
                        CollectionReference collectionReference4 = WelcomePageActivity.db_firestore.collection("Order_items");
                        collectionReference4.whereEqualTo("customer_id", customerid).get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        List<String> categories_results10 = new ArrayList<>();
                                        categories_results10.clear();
                                        int sumPrice = 0;
                                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                            Order_items order_items2 = documentSnapshot.toObject(Order_items.class);
                                            int id_produ = order_items2.getProduct_id();
                                            int quantity = order_items2.getQuantity();
                                            sumPrice += order_items2.getPrice();
                                            categories_results10.add("id: " + id_produ + " :Quantity: " + quantity + "\n");
                                        }

                                        sumorders.setItems(categories_results10.toString());
                                        sumorders.setPrice(sumPrice);
                                        WelcomePageActivity.db_firestore.collection("Orders").document(" " + customerid)
                                                .set(sumorders)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        // Handle the completion of setting the "sumorders" object in the "Orders" collection
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        // Handle the failure of setting the "sumorders" object in the "Orders" collection
                                                    }
                                                });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle the failure of retrieving the items from the "Order_items" collection
                                    }
                                });




                        WelcomePageActivity.db_firestore.collection("productsfirestore").document(" " + prod_id)
                                .update("quantity", quantityMinus)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });



                        List<ProductsDatabase> prod= WelcomePageActivity.myAppDatabase.myDao().getProductstoSetNewQuantity(prod_id);

                        String product_name="";
                        String product_description="";
                        String category_of="";
                        int price =0;


                        for (ProductsDatabase i: prod)
                        {
                           product_name=i.getProducts_name();
                           product_description= i.getProduct_description();
                           category_of = i.getCategory_of_prod();
                           price= i.getPrice();



                        }


                        ProductsDatabase product = new ProductsDatabase();
                        product.setQuantity_product_inside(quantityMinus);
                        product.setId(prod_id);
                        product.setProducts_name(product_name);
                        product.setProduct_description(product_description);
                        product.setCategory_of_prod(category_of);
                        product.setPrice(price);
                        WelcomePageActivity.myAppDatabase.myDao().updateProducts(product);









                        }else
                        {
                            activity.createNotifications("Insertion Failed", "There is not enough products");


                        }

                    } catch(Exception e){
                        String message = e.getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    }

                    quantity.setText("");
                    spinnerCust.setSelection(0);
                    spinnerProd.setSelection(0);

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new OrdersCustomerFragment());
                fragmentTransaction.addToBackStack(null); // Optional: Add to back stack if needed
                fragmentTransaction.commit();


            }

        });



        return view;
    }
}
