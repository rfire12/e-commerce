package com.example.e_commerce.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Adapters.CategoryAdapter;
import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryListFragment extends Fragment {
    FloatingActionButton floatCreate;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null) container.removeAllViews();

        View view = inflater.inflate(R.layout.category_list_fragment, container, false);

        floatCreate = view.findViewById(R.id.floating_new_category);

        floatCreate.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.frame_container, new AddCategoryFragment(), "addCategory").commit();
        });

        recyclerView = view.findViewById(R.id.category_list);

        DatabaseService databaseService = new DatabaseService(getContext());

        recyclerView.setAdapter(new CategoryAdapter(databaseService.getCategoriesForListing(), getContext()));

        return view;
    }
}
