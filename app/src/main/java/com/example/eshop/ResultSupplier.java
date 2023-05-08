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

public class ResultSupplier extends Fragment implements View.OnClickListener {
    TextView myTextView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerAdapter adapter;

    List<String> list = new ArrayList<>();




    Spinner spinner;
    Button search_button;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResultSupplier() {
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


                if (selected_item.equals("All Suppliers") && text_written.equals("")) {

                    List<SupplierDatabase> suppliers1 = WelcomePageActivity.myAppDatabase.myDao().getSuppliers();

                    for (SupplierDatabase i : suppliers1) {
                        int id = i.getId();

                        String Supplier_name = i.getSupplier_name();
                        String Supplier_nickname = i.getSupplier_nickname();
                        String phone= i.getPhone();
                        String location = i.getAddress();


                        list.add("id: " + String.valueOf(id) + "\n" + "Supplier name: " + Supplier_name + "\n" + "Supplier_nickname: " + Supplier_nickname+"\n"+"phone: "+phone+"\nlocation: "+location );


                    }


                }else if(selected_item.equals("All Suppliers"))
                {
                    List<SupplierDatabase> suppliers1 = WelcomePageActivity.myAppDatabase.myDao().getSuppliersWithVarName(text_written);
                    for (SupplierDatabase i : suppliers1) {
                        int id = i.getId();

                        String Supplier_name = i.getSupplier_name();
                        String Supplier_nickname = i.getSupplier_nickname();
                        String phone= i.getPhone();
                        String location = i.getAddress();


                        list.add("id: " + String.valueOf(id) + "\n" + "Supplier name: " + Supplier_name + "\n" + "Supplier_nickname: " + Supplier_nickname+"\n"+"phone: "+phone+"\nlocation: "+location );


                    }
                }else {

                     String parts[]=selected_item.split(" ");
                    List<SupplierDatabase> suppliers1 = WelcomePageActivity.myAppDatabase.myDao().getSuppliersWithVarID(Integer.parseInt(parts[0]));
                    for (SupplierDatabase i : suppliers1) {
                        int id = i.getId();

                        String Supplier_name = i.getSupplier_name();
                        String Supplier_nickname = i.getSupplier_nickname();
                        String phone= i.getPhone();
                        String location = i.getAddress();


                        list.add("id: " + String.valueOf(id) + "\n" + "Supplier name: " + Supplier_name + "\n" + "Supplier_nickname: " + Supplier_nickname+"\n"+"phone: "+phone+"\nlocation: "+location );


                    }





                }




                recyclerView = view.findViewById(R.id.recyclerview);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                //list = Arrays.asList(getResources().getStringArray(R.array.all_strings_username));

                adapter = new RecyclerAdapter(list);
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



