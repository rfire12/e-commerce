package com.example.e_commerce.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Adapter.ProductAdapter;
import com.example.e_commerce.Models.Product;
import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {

    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    ArrayList<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        productList = new ArrayList<>();

        loadProducts();
        displayData();

        FloatingActionButton fab = view.findViewById(R.id.floating_new_product);
        fab.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.frame_container, new ProductFragment()).commit();
        });

        return view;
    }

    public void loadProducts() {
        DatabaseService databaseService = new DatabaseService(getContext());
        productList = databaseService.getProductsForListing();
    }

    private void displayData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productAdapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(productAdapter);

        /*productAdapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = productList.get(recyclerView.getChildAdapterPosition(view)).getName();
                txtnombre.setText(nombre);
                Toast.makeText(getContext(), "Seleccionó: "+listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();

                interfaceComunicaFragments.enviarPersona(listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)));
            }
        });*/
    }
}
