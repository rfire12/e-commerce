package com.example.e_commerce.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_commerce.Interface.ItemClickListener;
import com.example.e_commerce.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textProductName, textProductPrice, textProductDescription;
    public ImageView imageView;
    public ItemClickListener listener;

    public ProductViewHolder(View itemView){
        super(itemView);
        imageView = itemView.findViewById(R.id.product_image);
        textProductName = itemView.findViewById(R.id.product_name);
        textProductPrice = itemView.findViewById(R.id.product_price);
        textProductDescription = itemView.findViewById(R.id.product_description);
    }
    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);
    }
}
