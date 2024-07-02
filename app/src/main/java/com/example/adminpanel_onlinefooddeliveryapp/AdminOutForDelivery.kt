package com.example.adminpanel_onlinefooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanel_onlinefooddeliveryapp.Adapter.DeliveryAdapter
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminOutForDeliveryBinding
import com.example.adminpanel_onlinefooddeliveryapp.databinding.DeliveryItemBinding

class AdminOutForDelivery : AppCompatActivity() {
    private lateinit var binding: ActivityAdminOutForDeliveryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminOutForDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val customername = arrayListOf("Abhishek jain","Girish Sharma","Anwit","Ayush Saklecha")
        val moneystatus = arrayListOf("recieved","not recieved","pending","recieved")
        val adapter = DeliveryAdapter(ArrayList(customername), ArrayList(moneystatus))
        binding.DeliveryRecyclerView.adapter = adapter

        binding.DeliveryRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}