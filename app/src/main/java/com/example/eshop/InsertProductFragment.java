package com.example.eshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */


public class InsertProductFragment extends Fragment {
    EditText id, name, description, price,quantity;
    Button submit_button;

    public eshopDatabase eshopDb;
    Spinner spinner , spinnerSelection ;

    public static MyDao dao;


    public InsertProductFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insert_product, container, false);



        List<CategoriesDatabase> cat = WelcomePageActivity.myAppDatabase.myDao().getCategories();
        List<String> categories_results = new ArrayList<>();
        for (CategoriesDatabase i: cat)
        {
            String category_name=i.getCategory_name();
            categories_results.add(category_name);

        }


        spinner = view.findViewById(R.id.insert_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories_results);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

            Bundle bundle = getArguments();


        });










        spinnerSelection = view.findViewById(R.id.insert_spinner);

        String category= spinnerSelection.getSelectedItem().toString();


        id = view.findViewById(R.id.product_id);
        name = view.findViewById(R.id.product_name);
        description = view.findViewById(R.id.product_description);
        price = view.findViewById(R.id.product_price);
        quantity = view.findViewById(R.id.product_quantity);

        submit_button = view.findViewById(R.id.insertProductSubmitButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Var_productid = 0;
                try {
                    Var_productid = Integer.parseInt(id.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }
                String Var_productname = name.getText().toString();
                String Var_product_description = description.getText().toString();

                int Var_productprice = 0;

                try {
                    Var_productprice = Integer.parseInt(price.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                int Var_quantity = 0;

                try {
                    Var_quantity = Integer.parseInt(quantity.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }



                try {
                    ProductsDatabase product = new ProductsDatabase();
                    product.setId(Var_productid);
                    product.setProducts_name(Var_productname);
                    product.setProduct_description(Var_product_description);
                    product.setPrice(Var_productprice);
                    product.setCategory_of_prod(category);
                    product.setQuantity_product_inside(Var_quantity);
                    WelcomePageActivity.myAppDatabase.myDao().insertProduct(product);

                    productsfirebase productsfirestore=new productsfirebase();
                    productsfirestore.setId_product(Var_productid);
                    productsfirestore.setName_product(Var_productname);
                    productsfirestore.setProduct_description(Var_product_description);
                    productsfirestore.setProduct_of_category(category);
                    productsfirestore.setProduct_price(Var_productprice);
                    productsfirestore.setQuantity(Var_quantity);

                    WelcomePageActivity.db_firestore.collection("productsfirestore").document(" "+Var_productid).
                            set(productsfirestore).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(),"Product added.",Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(),"add operation failed.",Toast.LENGTH_LONG).show();
                                }
                            });

                    Toast.makeText(getActivity(),"Record added.",Toast.LENGTH_LONG).show();





                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                id.setText("");
                name.setText("");
                description.setText("");
                price.setText("");
            }


        });
        return view;
    }
}
