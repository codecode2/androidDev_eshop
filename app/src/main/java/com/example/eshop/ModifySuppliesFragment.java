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


public class ModifySuppliesFragment extends Fragment {
    EditText id, product_id_sup, supplier_id_sup;
    Button submit_button;

    public ModifySuppliesFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.modify_supplies, container, false);
        id = view.findViewById(R.id.supplies_id_1);
        product_id_sup = view.findViewById(R.id.supplies_id_1);
        supplier_id_sup = view.findViewById(R.id.supplier_id_supplies1);

        submit_button = view.findViewById(R.id.modifySuppliesSubmitButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int var_supply_id = 0;
                try {
                    var_supply_id = Integer.parseInt(id.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                int var_product_id = 0;
                try {
                    var_product_id = Integer.parseInt(product_id_sup.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }

                int var_supplier_id = 0;
                try {
                    var_supplier_id = Integer.parseInt(supplier_id_sup.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println("Could not parse " + ex);
                }


                try {
                    SuppliesDatabase supplies = new SuppliesDatabase();
                    supplies.setId(var_supply_id);
                    supplies.setId_prod(var_product_id);
                    supplies.setId_sup(var_supplier_id);
                    


                    WelcomePageActivity.myAppDatabase.myDao().updateSupplies(supplies);
                    Toast.makeText(getActivity(),"Record added.",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    String message = e.getMessage();
                    Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                }
                id.setText("");
                product_id_sup.setText("");
                supplier_id_sup.setText("");

            }
        });
        return view;
    }
}
