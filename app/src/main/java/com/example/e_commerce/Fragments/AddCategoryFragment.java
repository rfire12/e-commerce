package com.example.e_commerce.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_commerce.Helpers.Encoding;
import com.example.e_commerce.Models.Category;
import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;

import java.io.InputStream;

public class AddCategoryFragment extends Fragment {
    EditText txtName;
    Button image_select, save, cancel;
    ImageView imageCat;
    Category category = new Category();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);

        txtName = view.findViewById(R.id.txt_category_name);
        imageCat = view.findViewById(R.id.create_cat_img);
        image_select = view.findViewById(R.id.btn_select_category_img);
        image_select.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        });

        save = view.findViewById(R.id.btn_save_cat);
        save.setOnClickListener(v -> {
            DatabaseService databaseService = new DatabaseService(getContext());
            databaseService.createCategory(new Category(txtName.getText().toString(), category.getImage()));
            Toast.makeText(getContext(), "Category added", Toast.LENGTH_SHORT);
            getFragmentManager().beginTransaction().replace(R.id.frame_container, new CategoryListFragment()).commit();
        });

        cancel = view.findViewById(R.id.btn_cancel_cat);
        cancel.setOnClickListener(v -> getFragmentManager().beginTransaction().replace(R.id.frame_container, new CategoryListFragment()).commit());

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputStream imageStream;

        try {
            imageCat.setImageURI(data.getData());
            imageStream = this.getActivity().getContentResolver().openInputStream(data.getData());
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            category.setImage(Encoding.encodeToBase64(selectedImage));
        } catch (Exception e) {

        }
    }
}