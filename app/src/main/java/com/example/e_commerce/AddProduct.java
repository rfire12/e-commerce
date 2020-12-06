package com.example.e_commerce;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddProduct extends AppCompatActivity {

    private String CategoryName;
    private Button AddNewProductButton;
    private EditText InputProductName, InputProductPrice;
    private ImageView InputProductImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        AddNewProductButton = findViewById(R.id.save_product_btn);
        InputProductImage = findViewById(R.id.product_image);
        InputProductName = findViewById(R.id.product_name);
        InputProductPrice = findViewById(R.id.product_price);
    }
}