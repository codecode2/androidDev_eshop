package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class ProductsFragment extends Fragment implements View.OnClickListener {

        Button insert_button;

    public ProductsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.productsfragment, container, false);
        insert_button = view.findViewById(R.id.insertButton);
        insert_button.setOnClickListener(this);

        Bundle bundle = getArguments();


        return  view;    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insertButton:
                WelcomePageActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertProductFragment()).addToBackStack(null).commit();
                break;

        }
    }


}

