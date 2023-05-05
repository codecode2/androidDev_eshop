package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SearchingFragment extends Fragment implements View.OnClickListener {

    Button product_search,supplier_search,supplies_search;

    ArrayAdapter<CharSequence> adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchingFragment() {
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
        View view = inflater.inflate(R.layout.searchingfragment, container, false);

        product_search = view.findViewById(R.id.searchProducts);
        product_search.setOnClickListener( this);
        supplier_search = view.findViewById(R.id.searchSupplier);
        supplier_search.setOnClickListener(this);
        supplies_search = view.findViewById(R.id.searchSupplies);
        supplies_search.setOnClickListener(this);






        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchProducts:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new SearchingProduct());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.searchSupplier:
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.replace(R.id.fragment_container, new SearchingSupplier());
                transaction2.addToBackStack(null);
                transaction2.commit();
                break;
            case R.id.searchSupplies:
                FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                transaction3.replace(R.id.fragment_container, new SearchingSupplies());
                transaction3.addToBackStack(null);
                transaction3.commit();
                break;




        }
    }


}



