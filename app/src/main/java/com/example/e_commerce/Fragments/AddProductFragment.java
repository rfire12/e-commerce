package com.example.e_commerce.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_commerce.Helpers.Encoding;
import com.example.e_commerce.Models.Category;
import com.example.e_commerce.Models.Product;
import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddProductFragment extends Fragment {
    Product product = new Product();
    EditText txtName, txtDescription, txtPrice;
    ImageView imageProd;
    Button save, cancel, update;
    ArrayList<Category> categories;
    List<String> categoryNames;
    Spinner spnCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        DatabaseService databaseService = new DatabaseService(getContext());

        categories = databaseService.getCategoriesForListing();
        categoryNames = databaseService.getCategories();

        txtName = view.findViewById(R.id.product_name);
        txtDescription = view.findViewById(R.id.product_description);
        txtPrice = view.findViewById(R.id.product_price);
        spnCategory = view.findViewById(R.id.product_category);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoryNames);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnCategory.setAdapter(arrayAdapter);

        imageProd = view.findViewById(R.id.product_image);
        imageProd.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        });

        save = view.findViewById(R.id.save_product_btn);
        save.setOnClickListener(v -> {
            databaseService.createProduct(new Product(txtName.getText().toString(), txtDescription.getText().toString(), Float.parseFloat(txtPrice.getText().toString()), product.getImage(), getIdFromName(spnCategory.getSelectedItem().toString())));
            Toast.makeText(getContext(), "Product Added", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction().replace(R.id.frame_container, new ProductListFragment()).commit();
        });

        cancel = view.findViewById(R.id.cancel_product_btn);
        cancel.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.frame_container, new ProductListFragment()).commit();
        });

        update = view.findViewById(R.id.update_product_btn);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            product.setId(bundle.getInt("id"));
            product = databaseService.getProductById(product.getId());

            txtName.setText(product.getName());
            txtPrice.setText(Float.toString(product.getPrice()));
            txtDescription.setText(product.getDescription());
            imageProd.setImageBitmap(Encoding.decodeToBitmap(product.getImage()));
            spnCategory.post(() -> spnCategory.setSelection(arrayAdapter.getPosition(getNameFromId(product.getCategoryId()))));

            save.setVisibility(View.GONE);

            // Update, visible
            update.setVisibility(View.VISIBLE);
        }

        update.setOnClickListener(v -> {
            databaseService.updateProduct(new Product(product.getId(), txtName.getText().toString(), txtDescription.getText().toString(), Float.parseFloat(txtPrice.getText().toString()), product.getImage(), getIdFromName(spnCategory.getSelectedItem().toString())));
            Toast.makeText(getContext(), "Product updated", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction().replace(R.id.frame_container, new ProductListFragment()).commit();
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream imageStream;

        try {
            imageStream = this.getActivity().getContentResolver().openInputStream(data.getData());
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            imageProd.setImageBitmap(selectedImage);
            product.setImage(Encoding.encodeToBase64(selectedImage));
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    private int getIdFromName(String name) {
        for (Category category : categories) {
            if (name.equalsIgnoreCase(category.getName())) {
                return category.getId();
            }
        }

        return 0;
    }

    private String getNameFromId(int id) {
        for (Category category : categories) {
            if (id == category.getId()) {
                return category.getName();
            }
        }

        return "";
    }
}
