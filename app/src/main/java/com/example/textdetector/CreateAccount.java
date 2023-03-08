package com.example.textdetector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class CreateAccount extends AppCompatActivity {

    TextView signinBtn;
    Button createAccountBtn;
    private EditText inputUsername, inputEmail, inputPassword, inputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        signinBtn = findViewById(R.id.SignIn);
        createAccountBtn = findViewById(R.id.CreateAccount_button);

        inputUsername = findViewById(R.id.userName_Input);
        inputEmail = findViewById(R.id.Email_Input);
        inputPassword = findViewById(R.id.Password_Input);
        inputConfirmPassword = findViewById(R.id.ConfirmPassword_InputLayout);



        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccount.this, LoginActivity.class));
            }
        });
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });
    }

    private void checkCredentials() {

        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmpassword = inputConfirmPassword.getText().toString();

        if (username.isEmpty() || username.length() < 10) {
            showError(inputUsername, "your username is not valid");
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email is not valid");
        } else if (password.isEmpty() || password.length() < 10) {
            showError(inputPassword, "Password is not valid");
        } else if (confirmpassword.isEmpty() || !confirmpassword.equals(password)) {
            showError(inputConfirmPassword, "Password not match");
        } else {
            Toast.makeText(this, "call Registration", Toast.LENGTH_LONG).show();
        }


    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}
