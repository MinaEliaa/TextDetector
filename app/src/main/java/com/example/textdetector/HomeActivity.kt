package com.example.textdetector

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
     lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.logout)
        button.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut();
            val i = Intent(this@HomeActivity, LoginActivity::class.java)
            startActivity(i)
        })


    }
}