package com.example.e_commerce.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_commerce.R;

public class ProductFragment extends Fragment {

    Button increase, decrease;
    int count;
    TextView detail_amount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment, container, false);
        increase = view.findViewById(R.id.detail_increase);
        decrease = view.findViewById(R.id.detail_decrease);
        count = Integer.parseInt(view.findViewById(R.id.detail_amount_text).toString());
        detail_amount = view.findViewById(R.id.detail_amount_text);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                detail_amount.setText(String.valueOf(count));
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count < 1)
                    count = 1;
                detail_amount.setText(String.valueOf(count));
            }
        });
        return view;
    }
}