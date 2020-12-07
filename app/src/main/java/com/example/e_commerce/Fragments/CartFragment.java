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

import androidx.recyclerview.widget.RecyclerView;
import com.example.e_commerce.R;

public class CartFragment extends Fragment {

    Button increase, decrease;
    int count;
    TextView cart_item_amount_txt;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        increase = view.findViewById(R.id.increase_amount_btn);
        decrease = view.findViewById(R.id.decrease_amount_btn);
        count = Integer.parseInt(view.findViewById(R.id.cart_item_amount).toString());
        cart_item_amount_txt = view.findViewById(R.id.cart_item_amount);
        recyclerView = view.findViewById(R.id.cart_recycler);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                cart_item_amount_txt.setText(String.valueOf(count));
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count < 1)
                    count = 1;
                cart_item_amount_txt.setText(String.valueOf(count));
            }
        });
        return view;
    }
}
