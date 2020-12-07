package com.example.e_commerce.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Adapter.ProductAdapter;
import com.example.e_commerce.Models.Product;
import com.example.e_commerce.R;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    ArrayList<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_fragment, container, false);
    }
}
