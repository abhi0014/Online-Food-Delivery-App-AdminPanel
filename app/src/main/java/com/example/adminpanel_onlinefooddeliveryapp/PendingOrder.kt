package com.example.adminpanel_onlinefooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanel_onlinefooddeliveryapp.Adapter.PendingOrderAdapter
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityPendingOrderBinding
import com.example.adminpanel_onlinefooddeliveryapp.databinding.PendingOrderItemBinding

class PendingOrder : AppCompatActivity() {
    private lateinit var binding: ActivityPendingOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val customer_name = arrayListOf("Abhishek","Ayush","Arpit")
        val food_quantity = arrayListOf("2","1","3")
        val food_image = arrayListOf(R.drawable.green_salad,R.drawable.tea,R.drawable.breakfast)

        val adapter = PendingOrderAdapter(ArrayList(customer_name),
            ArrayList(food_quantity), ArrayList
        (food_image)
        )

        binding.pendingOrderRecyclerView.adapter = adapter
        binding.pendingOrderRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}