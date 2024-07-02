package com.example.adminpanel_onlinefooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class Admin_Splash_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_splash_screen)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this,Admin_Login::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}