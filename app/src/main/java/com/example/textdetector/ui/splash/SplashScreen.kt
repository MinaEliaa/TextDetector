package com.example.textdetector.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.textdetector.ui.login.LoginActivity
import com.example.textdetector.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper())
            .postDelayed(
                { starthomeactivity() }, 2000
            )
    }

    fun starthomeactivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()

    }

}
