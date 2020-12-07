package com.example.e_commerce.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Adapters.CategoryAdapter;
import com.example.e_commerce.Models.Category;
import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {
    FloatingActionButton floatCreate;
    RecyclerView recyclerView;
    ArrayList<Category> categories;
    CategoryAdapter categoryAdapter;

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

        categories = databaseService.getCategoriesForListing();

        categoryAdapter = new CategoryAdapter(getContext(), categories);

        categoryAdapter.setOnClickListener(v -> {
            Category category = categories.get(recyclerView.getChildAdapterPosition(v));

            Bundle bundle = new Bundle();
            bundle.putInt("id", category.getId());

            AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
            addCategoryFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame_container, addCategoryFragment).commit();
        });

        recyclerView.setAdapter(categoryAdapter);

        return view;
    }
}
