package com.example.adminpanel_onlinefooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityCreateNewUserBinding

class CreateNewUser : AppCompatActivity() {
    private lateinit var binding : ActivityCreateNewUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}