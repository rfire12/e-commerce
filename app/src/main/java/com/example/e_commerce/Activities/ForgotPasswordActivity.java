package com.example.e_commerce.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button getPassButton;
    private TextView loginNowTextView;
    EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        txtEmail = findViewById(R.id.txt_login_email);

        getPassButton = findViewById(R.id.getPassButton);
        getPassButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatabaseService databaseService = new DatabaseService(ForgotPasswordActivity.this);

                String password = databaseService.updatePassword(txtEmail.getText().toString());

                Toast.makeText(ForgotPasswordActivity.this, password, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        loginNowTextView = findViewById(R.id.loginNowTextView);
        loginNowTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}