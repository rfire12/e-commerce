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
        return view;
    }

    public void loadProducts(){
        productList.add(new Product("Dell PC","i7 1T SSD",2000, null, 0));
        productList.add(new Product("HP PC","i5 256T SSD",300, null, 0));
    }

    private void displayData(){
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
