package com.example.textdetector.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.textdetector.R;
import com.example.textdetector.ui.forgetpassword.ForgetPassword;
import com.example.textdetector.ui.home.HomeActivity;
import com.example.textdetector.ui.signup.CreateAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private ProgressBar progressBar ;
    TextView signUpBtn;
    TextView forgetPasswordBtn;
    Button loginBtn;
    EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        signUpBtn = findViewById(R.id.SignUp);
        forgetPasswordBtn = findViewById(R.id.forget_password);
        loginBtn = findViewById(R.id.login_Button);

        inputEmail = (EditText) findViewById(R.id.et_email_login);
        inputPassword = (EditText) findViewById(R.id.et_password_login);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (checkCredentials()) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        LoginToast();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(i);
                                    } else {

                                    }
                                }
                            });
                }
            }
        });

        forgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateAccount.class));
            }
        });


    }


    private Boolean checkCredentials() {
        Boolean isVaild = true;
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@") || !email.contains(".com")) {
            showError(inputEmail, "Email is not valid");
            isVaild = false;
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password is not valid");
            isVaild = false;
        } else {
            isVaild = true;


        }
        return isVaild;
    }



    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    private void LoginToast(){
        LayoutInflater inflater=getLayoutInflater();
        View view = inflater.inflate(R.layout.login_successful_toast,this.findViewById(R.id.Toast_login));
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }




}


