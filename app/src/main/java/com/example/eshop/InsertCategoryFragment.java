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


public class InsertCategoryFragment extends Fragment {
    EditText id, name, description;
    Button submit_button;

    public InsertCategoryFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.insert_category, container, false);
        id = view.findViewById(R.id.category_id);
        name = view.findViewById(R.id.category_name);
        description = view.findViewById(R.id.category_description);

        WelcomePageActivity activity = (WelcomePageActivity) getActivity();

        submit_button = view.findViewById(R.id.insertCategorySubmitButton);
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
                    CategoriesDatabase category = new CategoriesDatabase();
                    category.setId(Var_productid);
                    category.setCategory_name(Var_productname);
                    category.setCategory_description(Var_product_description);

                    WelcomePageActivity.myAppDatabase.myDao().insertCategory(category);

                    CategoriesDatabase catDatabase= new CategoriesDatabase();
                    catDatabase.setId(Var_productid);
                    catDatabase.setCategory_name(Var_productname);
                    catDatabase.setCategory_description(Var_product_description);

                    WelcomePageActivity.db_firestore.collection("categories").document(" "+Var_productid).
                            set(catDatabase).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    activity.createNotifications("Insertion Success","The record inserted succesfully");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });


                } catch (Exception e) {
                    String message = e.getMessage();
                    activity.createNotifications("Insertion Failed","The record is not inserted");
                }
                id.setText("");
                name.setText("");
                description.setText("");

            }
        });
        return view;
    }
}
