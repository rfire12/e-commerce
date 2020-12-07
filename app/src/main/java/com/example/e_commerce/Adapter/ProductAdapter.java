package com.example.e_commerce.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.Models.Product;
import com.example.e_commerce.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements View.OnClickListener {
    LayoutInflater inflater;
    ArrayList<Product> model;

    private View.OnClickListener listener;

    public ProductAdapter(Context context, ArrayList<Product> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_list_layout, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int productId = model.get(position).getId();
        float productPrice = model.get(position).getPrice();
        String description = model.get(position).getName() + ' ' + model.get(position).getDescription();
        String imageBase64 = model.get(position).getImage();

        holder.productIdView.setText(Integer.toString(productId));
        holder.productPriceView.setText(Float.toString(productPrice));
        holder.productDescriptionView.setText(description);

        // Base 64 here
        /*byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.productImageView.setImageBitmap(decodedByte);*/
    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productIdView, productPriceView, productDescriptionView;

        public ViewHolder(@NonNull final View view) {
            super(view);
            productIdView = view.findViewById(R.id.productIdView);
            productPriceView = view.findViewById(R.id.productPriceView);
            productDescriptionView = view.findViewById(R.id.productDescriptionView);
            productImageView = view.findViewById(R.id.productImageView);
        }
    }
}
