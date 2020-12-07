package com.example.e_commerce.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_commerce.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryListFragment extends Fragment {
    FloatingActionButton floatCreate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container != null) container.removeAllViews();

        View view = inflater.inflate(R.layout.category_list_fragment, container, false);

        floatCreate = view.findViewById(R.id.floating_new_category);

        floatCreate.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.frame_container, new AddCategoryFragment(), "addCategory").commit();
        });

        return view;
    }
}
