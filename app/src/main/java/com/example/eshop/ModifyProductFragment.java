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
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */


public class ModifyProductFragment extends Fragment {
    EditText id, name, description, price,quantity;
    Button submit_button;
    String category;

    Spinner spinner , spinnerSelection ;
    public ModifyProductFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.modify_product, container, false);
        WelcomePageActivity activity = (WelcomePageActivity) getActivity();

        List<CategoriesDatabase> cat = WelcomePageActivity.myAppDatabase.myDao().getCategories();
        List<String> categories_results = new ArrayList<>();
        for (CategoriesDatabase i: cat)
        {
            String category_name=i.getCategory_name();
            categories_results.add(category_name);

        }


        spinner = view.findViewById(R.id.insert_spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories_results);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               category= spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

            Bundle bundle = getArguments();


        });







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
                String var_category= category.toString();
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
                    product.setCategory_of_prod(var_category);
                    product.setQuantity_product_inside(Var_quantity);
                    WelcomePageActivity.myAppDatabase.myDao().updateProducts(product);
                    Toast.makeText(getActivity(),"Modify added.",Toast.LENGTH_LONG).show();

                    productsfirebase productsfirestore=new productsfirebase();
                    productsfirestore.setId_product(Var_productid);
                    productsfirestore.setName_product(Var_productname);
                    productsfirestore.setProduct_description(Var_product_description);
                    productsfirestore.setProduct_of_category(var_category);
                    productsfirestore.setProduct_price(Var_productprice);
                    productsfirestore.setQuantity(Var_quantity);

                    WelcomePageActivity.db_firestore.collection("productsfirestore").document(" "+Var_productid).
                            set(productsfirestore).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    activity.createNotifications("Modify Success","The record modified succesfully");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    activity.createNotifications("Modify Failed","The record is not modified");
                                }
                            });


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
