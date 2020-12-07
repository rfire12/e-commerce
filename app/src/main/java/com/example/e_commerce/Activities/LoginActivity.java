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

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private TextView forgotPassTextView;
    private TextView signUpTextView;

    EditText txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide(); // Hide header

        loginButton = findViewById(R.id.loginButton);
        forgotPassTextView = findViewById(R.id.forgotPassTextView);
        signUpTextView = findViewById(R.id.signUpTextView);

        txtEmail = findViewById(R.id.txt_login_email);
        txtPassword = findViewById(R.id.txt_login_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseService databaseService = new DatabaseService(LoginActivity.this);
                User user = databaseService.login(txtEmail.getText().toString(), txtPassword.getText().toString());

                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgotPassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}