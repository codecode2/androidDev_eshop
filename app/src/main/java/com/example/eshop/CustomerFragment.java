package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CustomerFragment extends Fragment implements View.OnClickListener {

        Button insert_button,modify_button,delete_button,orders_button;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.customerfragment, container, false);
        insert_button = view.findViewById(R.id.insertButton1);
        insert_button.setOnClickListener(this);
        modify_button = view.findViewById(R.id.modifyButton1);
        modify_button.setOnClickListener(this);
        delete_button = view.findViewById(R.id.deleteButton1);
        delete_button.setOnClickListener(this);
        orders_button = view.findViewById(R.id.orderProduct1);
        orders_button.setOnClickListener(this);

        Bundle bundle = getArguments();


        return  view;    }



    public static InsertProductFragment newInstance(String param1, String param2) {
        InsertProductFragment fragment = new InsertProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insertButton1:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new InsertCustomerFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.modifyButton1:
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.replace(R.id.fragment_container, new ModifyCustomerFragment());
                transaction2.addToBackStack(null);
                transaction2.commit();
                break;
            case R.id.deleteButton1:
                FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                transaction3.replace(R.id.fragment_container, new DeleteCustomerFragment());
                transaction3.addToBackStack(null);
                transaction3.commit();
                break;

            case R.id.orderProduct1:
                FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                transaction4.replace(R.id.fragment_container, new OrdersCustomerFragment());
                transaction4.addToBackStack(null);
                transaction4.commit();
                break;
        }
    }

}

