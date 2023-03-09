package com.example.textdetector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CreateAccount extends AppCompatActivity {

    TextView signinBtn;
    Button createAccountBtn;
    EditText inputUsername, inputEmail, inputPassword, inputConfirmPassword;
    private FirebaseAuth mAuth;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        signinBtn = findViewById(R.id.SignIn);
        createAccountBtn = findViewById(R.id.CreateAccount_button);
        ImageView BackBlueArrow = findViewById(R.id.backarrow);


        inputUsername =(EditText) findViewById(R.id.et_Username);
        inputEmail = (EditText)findViewById(R.id.et_email);
        inputPassword =(EditText) findViewById(R.id.et_password);
        inputConfirmPassword = (EditText)findViewById(R.id.et_confirmpassword);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        BackBlueArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccount.this,LoginActivity.class));
            }
        });
        createAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();
                Log.d("email", "onCreate: "+email + password);
                //checkCredentials();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent i = new Intent(CreateAccount.this,HomeActivity.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                }
                            }
                        });
            }
        });


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAccount.this, LoginActivity.class));
            }
        });



    }

    private void checkCredentials() {

        String username = inputUsername.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmpassword = inputConfirmPassword.getText().toString();

        if (username.isEmpty() ) {
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