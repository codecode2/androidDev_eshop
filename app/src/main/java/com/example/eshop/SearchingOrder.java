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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class SearchingOrder extends Fragment implements View.OnClickListener {

    Spinner spinner;

    Button search_button;


    EditText searchInput ;
    ArrayAdapter<CharSequence> adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchingOrder() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.searchorders, container, false);
        search_button = view.findViewById(R.id.searchButton5);
        searchInput= view.findViewById(R.id.searchInput5);
        search_button.setOnClickListener(this);

        List<String> categories_results = new ArrayList<>();
        List<SupplierDatabase> categories= WelcomePageActivity.myAppDatabase.myDao().getSuppliers();


        categories_results.add("All Orders");
        for (SupplierDatabase i: categories)
        {
            String Name=i.getSupplier_name();
            String nickname=i.getSupplier_nickname();
            int id=i.getId();

            categories_results.add(id + " "+Name +" "+ nickname);

        }


        spinner = view.findViewById(R.id.spinnerSearch5);
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
        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.searchButton5:

                String message=spinner.getSelectedItem().toString();
                String message2= searchInput.getText().toString();
                Bundle result = new Bundle();
                result.putString("df1", message);
                result.putString("df2", message2);

                getParentFragmentManager().setFragmentResult("DataFromProduct",result);


                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new ResultOrder());
                transaction.addToBackStack(null);
                transaction.commit();
                break;

        }

    }
}



