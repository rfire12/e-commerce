package com.example.e_commerce.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Models.User;
import com.example.e_commerce.R;
import com.example.e_commerce.Services.DatabaseService;

public class SignUpActivity extends AppCompatActivity {

    private Button registerButton;
    private TextView forgotPassTextView;
    private TextView loginNowTextView;

    EditText txtName, txtUsername, txtEmail, txtPassword, txtConfirmPassword, txtContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerButton = findViewById(R.id.btn_register);
        forgotPassTextView = findViewById(R.id.forgotPassTextView);
        loginNowTextView = findViewById(R.id.loginNowTextView);

        txtName = findViewById(R.id.txt_user_name);
        txtUsername = findViewById(R.id.txt_user_username);
        txtEmail = findViewById(R.id.txt_user_email);
        txtPassword = findViewById(R.id.txt_user_password);
        txtConfirmPassword = findViewById(R.id.txt_user_confirm_password);
        txtContact = findViewById(R.id.txt_user_contact);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtPassword.getText().toString().equalsIgnoreCase(txtConfirmPassword.getText().toString())) {
                    DatabaseService databaseService = new DatabaseService(SignUpActivity.this);
                    databaseService.registerUser(new User(txtName.getText().toString(), txtUsername.getText().toString(), txtEmail.getText().toString(), txtPassword.getText().toString(), txtContact.getText().toString()));

                    Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "Passwords must match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        loginNowTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}