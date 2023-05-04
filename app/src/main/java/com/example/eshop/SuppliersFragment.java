package com.example.eshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SuppliersFragment extends Fragment implements View.OnClickListener {
    Button insert_button,modify_button,delete_button;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SuppliersFragment() {
        // Required empty public constructor
    }

    public static MenuUploadFragment newInstance(String param1, String param2) {
        MenuUploadFragment fragment = new MenuUploadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.suppliersfragment, container, false);

        insert_button = view.findViewById(R.id.insertButton);
        insert_button.setOnClickListener(this);
        modify_button = view.findViewById(R.id.modifyButton);
        modify_button.setOnClickListener(this);
        delete_button = view.findViewById(R.id.deleteButton);
        delete_button.setOnClickListener(this);

        Bundle bundle = getArguments();

        return  view;    }





    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insertButton:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new InsertSupplierFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.modifyButton:
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.replace(R.id.fragment_container, new ModifySupplierFragment());
                transaction2.addToBackStack(null);
                transaction2.commit();
                break;
            case R.id.deleteButton:
                FragmentTransaction transaction3 = getFragmentManager().beginTransaction();
                transaction3.replace(R.id.fragment_container, new DeleteSupplierFragment());
                transaction3.addToBackStack(null);
                transaction3.commit();
                break;
        }
    }

}

