package com.example.adminpanel_onlinefooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanel_onlinefooddeliveryapp.Adapter.All_Item_Adapter
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminAllItemBinding

class Admin_All_Item : AppCompatActivity() {
    private lateinit var binding : ActivityAdminAllItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAllItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val menuFoodName = listOf("BreakFast", "Green Salad", "Rolls")
        val menuFoodPrice = listOf("$10", "$15", "$20")
        val menuFoodImage = listOf(R.drawable.breakfast, R.drawable.green_salad, R.drawable.rolls)

        val adapter = All_Item_Adapter(
            ArrayList(menuFoodName),
            ArrayList(menuFoodPrice),
            ArrayList(menuFoodImage)
        )

        binding.MenuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter = adapter
    }
}