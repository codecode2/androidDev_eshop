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
import android.widget.TextView;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultProduct extends Fragment implements AdapterView.OnItemSelectedListener {
    TextView myTextView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list;
    private RecyclerAdapter adapter;

    String  selected_item,text_written;
    List<String> product_list = new ArrayList<>();




    Spinner spinner;
    Button search_button;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultProduct() {
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
                 selected_item = result.getString("df1");
                 text_written = result.getString("df2");


                if (selected_item.equals("All Products") && text_written.equals("")) {

                    List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProducts();

                    for (ProductsDatabase i : products1) {
                        int id1 = i.getId();
                        String product_name = i.getProducts_name();
                        String category_of_product = i.getCategory_of_prod();
                        String description = i.getProduct_description();
                        int price = i.getPrice();
                        int quantity = i.getQuantity_product_inside();


                        product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                    }


                }else if(selected_item.equals("All Products"))
                {

                    List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarName(text_written);

                    for (ProductsDatabase i : products1) {
                        int id1 = i.getId();
                        String product_name = i.getProducts_name();
                        String category_of_product = i.getCategory_of_prod();
                        String description = i.getProduct_description();
                        int price = i.getPrice();
                        int quantity = i.getQuantity_product_inside();


                        product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                    }
                }
                else if(text_written.equals(""))
                {
                    List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarCategory(selected_item);


                    for (ProductsDatabase i : products1) {
                        int id1 = i.getId();
                        String product_name = i.getProducts_name();
                        String category_of_product = i.getCategory_of_prod();
                        String description = i.getProduct_description();
                        int price = i.getPrice();
                        int quantity = i.getQuantity_product_inside();


                        product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                    }
                }

                else
                {
                    List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithTogether(selected_item,text_written);


                    for (ProductsDatabase i : products1) {
                        int id1 = i.getId();
                        String product_name = i.getProducts_name();
                        String category_of_product = i.getCategory_of_prod();
                        String description = i.getProduct_description();
                        int price = i.getPrice();
                        int quantity = i.getQuantity_product_inside();


                        product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                    }

                }



                Spinner spinner = view.findViewById(R.id.spinnerSearch);

                ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(getContext(), R.array.spinner_search_values, android.R.layout.simple_spinner_item);

                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(adapterSpinner);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        String selectedItem = parent.getItemAtPosition(position).toString();
                        if (selectedItem.equals(""))
                        {
                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProducts();
                                product_list.clear();
                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }else if(selected_item.equals("All Products"))
                            {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarName(text_written);
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }else if(text_written.equals(""))
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarCategory(selected_item);
                                product_list.clear();


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            } else
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithTogether(selected_item,text_written);


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }
                        }

                        else if (selectedItem.equals("Order by Price")) {
                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().OrderByPrice();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }else if(selected_item.equals("All Products"))
                            {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarNameOrderByPrice(text_written);
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }else if(text_written.equals(""))
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarCategoryOrderByPrice(selected_item);
                                product_list.clear();


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            } else
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithTogetherOrderByPrice(selected_item,text_written);


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }

                        } else if (selectedItem.equals("Order by Id"))


                        {  if (selected_item.equals("All Products") && text_written.equals("")) {

                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().OrderById();
                            product_list.clear();

                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                        } else if(selected_item.equals("All Products"))
                        {

                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarNameOrderById(text_written);
                            product_list.clear();

                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                        }else if(text_written.equals(""))
                        {
                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarCategoryOrderById(selected_item);
                            product_list.clear();


                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                        }
                        else
                        {
                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithTogetherOrderById(selected_item,text_written);


                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                         }

                        }else if (selectedItem.equals("Order by Name"))
                        {
                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().OrderByName();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }else if(selected_item.equals("All Products"))
                            {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarNameOrderByName(text_written);
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            } else if(text_written.equals(""))
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarCategoryOrderByName(selected_item);
                                product_list.clear();


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }
                            else
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithTogetherOrderByName(selected_item,text_written);


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }

                        }else if (selectedItem.equals("Order by Quantity"))
                        {  if (selected_item.equals("All Products") && text_written.equals("")) {

                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().OrderByNameQuantity();
                            product_list.clear();

                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                        } else if(selected_item.equals("All Products"))
                        {

                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarNameOrderByQuantity(text_written);
                            product_list.clear();

                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                        } else if(text_written.equals(""))
                        {
                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarCategoryOrderByQuantity(selected_item);
                            product_list.clear();


                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                        }
                        else
                        {
                            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithTogetherOrderByQuantity(selected_item,text_written);


                            for (ProductsDatabase i : products1) {
                                int id1 = i.getId();
                                String product_name = i.getProducts_name();
                                String category_of_product = i.getCategory_of_prod();
                                String description = i.getProduct_description();
                                int price = i.getPrice();
                                int quantity = i.getQuantity_product_inside();


                                product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                            }
                            adapter.notifyDataSetChanged();

                        }


                        }else if (selectedItem.equals("Order by Category"))
                        {
                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().OrderByNameCategory();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }else if(selected_item.equals("All Products"))
                            {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarNameOrderByCategory(text_written);
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            } else if(text_written.equals(""))
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithVarCategoryOrderByCategory(selected_item);
                                product_list.clear();


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }

                            else
                            {
                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsWithTogetherOrderByCategory(selected_item,text_written);


                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();

                            }

                        } else if (selectedItem.equals("Price Over 10")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsPriceGT();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }



                        }else if (selectedItem.equals("Price Under 10")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsPriceLT();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }else if (selectedItem.equals("Quantity Over 5")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsQuantityGT();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }else if (selectedItem.equals("Quantity Over 5")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsQuantityGT();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }else if (selectedItem.equals("Quantity Under 5")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsQuantityLT();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }else if (selectedItem.equals("Price MAX")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsPriceMAX();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }else if (selectedItem.equals("Price MIN")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsPriceMIN();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }else if (selectedItem.equals("Quantity MAX")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsQuantityMAX();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }else if (selectedItem.equals("Quantity MIN")) {

                            if (selected_item.equals("All Products") && text_written.equals("")) {

                                List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().getProductsQuantityMIN();
                                product_list.clear();

                                for (ProductsDatabase i : products1) {
                                    int id1 = i.getId();
                                    String product_name = i.getProducts_name();
                                    String category_of_product = i.getCategory_of_prod();
                                    String description = i.getProduct_description();
                                    int price = i.getPrice();
                                    int quantity = i.getQuantity_product_inside();


                                    product_list.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Price: " + price + "\nQuantity: " + quantity);

                                }
                                adapter.notifyDataSetChanged();


                            }

                        }








                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Handle no item selected for the specific spinner
                    }
                });






                recyclerView = view.findViewById(R.id.recyclerview);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);

                adapter = new RecyclerAdapter(product_list);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);





            }

        });



        return view;

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();

        EditText test = view.findViewById(R.id.result_text);
         test.setText("i am in");

        if (selected_item.equals("All Products") && text_written.equals("")) {

            List<ProductsDatabase> products1 = WelcomePageActivity.myAppDatabase.myDao().OrderByPrice();
            List<String> newProductList = new ArrayList<>();

            for (ProductsDatabase i : products1) {
                int id1 = i.getId();
                String product_name = i.getProducts_name();
                String category_of_product = i.getCategory_of_prod();
                String description = i.getProduct_description();
                int quantity = i.getQuantity_product_inside();


                newProductList.add("id: " + String.valueOf(id1) + "\n" + "Product name: " + product_name + "\n" + "Description: " + description + "\n" + "Category: " + category_of_product + "\n" + "Quantity: " + quantity);

            }
            product_list.clear();
            product_list.addAll(newProductList);

            adapter.notifyDataSetChanged();
        }





    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



