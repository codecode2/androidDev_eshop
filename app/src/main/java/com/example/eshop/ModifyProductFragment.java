package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */


public class ModifyProductFragment extends Fragment {
    EditText id, name, description, price;
    Button submit_button;

    public ModifyProductFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.modify_product, container, false);
        id = view.findViewById(R.id.product_id);
        name = view.findViewById(R.id.product_name);
        description = view.findViewById(R.id.product_description);
        price = view.findViewById(R.id.product_price);
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

                try {
                    ProductsDatabase product = new ProductsDatabase();
                    product.setId(Var_productid);
                    product.setProducts_name(Var_productname);
                    product.setProduct_description(Var_product_description);
                    product.setPrice(Var_productprice);
                    WelcomePageActivity.myAppDatabase.myDao().updateProducts(product);
                    Toast.makeText(getActivity(),"Modify added.",Toast.LENGTH_LONG).show();
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
