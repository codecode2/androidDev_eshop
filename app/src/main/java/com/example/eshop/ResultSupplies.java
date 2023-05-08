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

import java.util.ArrayList;
import java.util.List;

public class ResultSupplies extends Fragment implements View.OnClickListener {
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

    public ResultSupplies() {
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

                if (selected_item.equals("All Supplies") && text_written.equals("")) {

                    List<ProductAndSuppliers> products1 = WelcomePageActivity.myAppDatabase.myDao().getSuppliersAndProducts();

                    for (ProductAndSuppliers i : products1) {
                          int idProduct= i.getProducts_id();
                          String product_name=i.getProducts_name();
                          String description=i.getProduct_description();
                          String category_of_product=i.getCategory_of_product();
                          double price = i.getPrice();
                          int quantity= i.getQuantity_product();

                          int supplierid=i.getSupplier_id();
                          String name= i.getSupplier_name();
                          String nickname=i.getSupplier_nickname();
                        product_list.add("\nSupplier id: " + supplierid+ "\nName: "+name+"\nNickname: "+nickname+
                                "\nProduct id: " + String.valueOf(idProduct) + "\n" + "Product name: " + product_name + "\n"   + "Category: " + category_of_product + "\n" + "Quantity: " + quantity);
                    }
                }else if (selected_item.equals("All Supplies"))
                {

                    List<ProductAndSuppliers> products1 = WelcomePageActivity.myAppDatabase.myDao().getSuppliersAndProducts2(text_written);

                    for (ProductAndSuppliers i : products1) {
                        int idProduct= i.getProducts_id();
                        String product_name=i.getProducts_name();
                        String description=i.getProduct_description();
                        String category_of_product=i.getCategory_of_product();
                        double price = i.getPrice();
                        int quantity= i.getQuantity_product();

                        int supplierid=i.getSupplier_id();
                        String name= i.getSupplier_name();
                        String nickname=i.getSupplier_nickname();
                        product_list.add("\nSupplier id: " + supplierid+ "\nName: "+name+"\nNickname: "+nickname+
                                "\nProduct id: " + String.valueOf(idProduct) + "\n" + "Product name: " + product_name + "\n"   + "Category: " + category_of_product + "\n" + "Quantity: " + quantity);
                    }
                }else

                {
                    List<ProductAndSuppliers> products1 = WelcomePageActivity.myAppDatabase.myDao().getSuppliersAndProducts3(Integer.parseInt(parts[0]));

                    for (ProductAndSuppliers i : products1) {
                        int idProduct= i.getProducts_id();
                        String product_name=i.getProducts_name();
                        String description=i.getProduct_description();
                        String category_of_product=i.getCategory_of_product();
                        double price = i.getPrice();
                        int quantity= i.getQuantity_product();

                        int supplierid=i.getSupplier_id();
                        String name= i.getSupplier_name();
                        String nickname=i.getSupplier_nickname();
                        product_list.add("\nSupplier id: " + supplierid+ "\nName: "+name+"\nNickname: "+nickname+
                                "\nProduct id: " + String.valueOf(idProduct) + "\n" + "Product name: " + product_name + "\n"   + "Category: " + category_of_product + "\n" + "Quantity: " + quantity);
                    }




                }









                recyclerView = view.findViewById(R.id.recyclerview);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                //list = Arrays.asList(getResources().getStringArray(R.array.all_strings_username));

                adapter = new RecyclerAdapter(product_list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);



            }

        });



        return view;

    }

    @Override
    public void onClick(View v) {



    }
}



