package com.example.adminpanel_onlinefooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminProfileBinding

class AdminProfile : AppCompatActivity() {
    private lateinit var  binding : ActivityAdminProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profileName.isEnabled=false
        binding.profileEmail.isEnabled=false
        binding.profileAddress.isEnabled=false
        binding.profilePhone.isEnabled=false
        binding.viewPassword.isEnabled=false

        var isEnable = false
        binding.editProfile.setOnClickListener {
            isEnable = true
            binding.profileName.isEnabled=isEnable
            binding.profileEmail.isEnabled=isEnable
            binding.profileAddress.isEnabled=isEnable
            binding.profilePhone.isEnabled=isEnable
            binding.viewPassword.isEnabled=isEnable
        }

        binding.saveInformation.setOnClickListener {
            if (isEnable == true)
            {
                isEnable = false
                binding.profileName.isEnabled=false
                binding.profileEmail.isEnabled=false
                binding.profileAddress.isEnabled=false
                binding.profilePhone.isEnabled=false
                binding.viewPassword.isEnabled=false
            }
        }
        binding.backButton.setOnClickListener {
            finish()
        }


    }
}


