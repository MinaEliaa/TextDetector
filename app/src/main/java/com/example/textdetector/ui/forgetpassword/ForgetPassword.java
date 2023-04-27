package com.example.textdetector.ui.forgetpassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.textdetector.R;
import com.example.textdetector.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    private EditText forEmail;
    private Button forgetbtn;
    private String email;
    ProgressDialog dialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forget_password);

        forEmail=findViewById(R.id.forget_Email);
        forgetbtn=findViewById(R.id.forget_button);

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = forEmail.getText().toString();
                if(email.isEmpty() || !email.contains("@") || !email.contains(".com")){
                    showError(forEmail, "Email is not valid");
                }
                else {
                    forgetPassword();
                }
            }

            private void forgetPassword() {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    //Toast
                                    CheckToast();
                                    startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
                                    finish();
                                } else {
                                    //Toast
                                    Toast.makeText(ForgetPassword.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            private void CheckToast() {
                                LayoutInflater inflater = getLayoutInflater();
                                View view = inflater.inflate(R.layout.check_successful_toast, findViewById(R.id.Toast_check));
                                Toast toast = new Toast(ForgetPassword.this); // or getApplicationContext()
                                toast.setDuration(Toast.LENGTH_SHORT);
                                toast.setView(view);
                                toast.show();
                            }
                        });
            }
        });





        ImageView BackBlueArrow = findViewById(R.id.backarrow);
        BackBlueArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
            }
        });

        TextView taptologinBtn = findViewById(R.id.tapToLogIn);
        taptologinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword.this,LoginActivity.class));
            }
        });
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

}