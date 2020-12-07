package com.example.e_commerce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Helpers.Encoding;
import com.example.e_commerce.Models.Category;
import com.example.e_commerce.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> implements View.OnClickListener {
    LayoutInflater inflater;
    ArrayList<Category> model;

    private View.OnClickListener listener;

    public CategoryAdapter(Context context, ArrayList<Category> model) {
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_item, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = model.get(position);
        holder.categoryImage.setImageBitmap(Encoding.decodeToBitmap(category.getImage()));
        holder.categoryName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Fields
        ImageView categoryImage;
        TextView categoryName;

        public MyViewHolder(View view) {
            super(view);
            categoryImage = view.findViewById(R.id.cat_image);
            categoryName = view.findViewById(R.id.cat_name);
        }
    }
}
