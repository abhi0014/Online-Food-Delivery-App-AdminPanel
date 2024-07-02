package com.example.adminpanel_onlinefooddeliveryapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminAddItemBinding

class Admin_Add_Item : AppCompatActivity() {
    private lateinit var binding: ActivityAdminAddItemBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }
    }
  private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            binding.selectedImage.setImageURI(uri)

        }
    }
}
