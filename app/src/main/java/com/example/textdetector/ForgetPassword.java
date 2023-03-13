package com.example.textdetector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPassword extends AppCompatActivity {



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        ImageView BackBlueArrow = findViewById(R.id.backarrow);
        BackBlueArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPassword.this,LoginActivity.class));
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


}