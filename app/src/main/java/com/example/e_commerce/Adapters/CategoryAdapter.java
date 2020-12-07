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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    ArrayList<Category> categories;
    Context context;

    public CategoryAdapter(ArrayList<Category> categories, Context context){
        this.categories = categories;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryImage.setImageBitmap(Encoding.decodeToBitmap(category.getImage()));
        holder.categoryName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        // Fields
        ImageView categoryImage;
        TextView categoryName;

        public MyViewHolder(View view){
            super(view);
            categoryImage = view.findViewById(R.id.cat_image);
            categoryName = view.findViewById(R.id.cat_name);
        }
    }
}
