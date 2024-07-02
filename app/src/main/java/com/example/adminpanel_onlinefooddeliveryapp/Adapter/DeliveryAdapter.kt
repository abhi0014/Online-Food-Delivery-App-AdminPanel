package com.example.adminpanel_onlinefooddeliveryapp.Adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel_onlinefooddeliveryapp.databinding.DeliveryItemBinding

class DeliveryAdapter(private val customerNames:ArrayList<String>,private val moneyStatus:ArrayList<String>): RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = DeliveryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DeliveryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return customerNames.size
    }
    inner class DeliveryViewHolder(private val binding : DeliveryItemBinding ):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = customerNames[position]
                Paymentstatus.text = moneyStatus[position]

                val colorMap = mapOf(
                    "recieved" to Color.GREEN,
                    "not recieved" to Color.RED,
                    "pending" to Color.GRAY
                )

                Paymentstatus.setTextColor(colorMap[moneyStatus[position]]?:Color.BLACK)
                                                                     // by defalut color black
                statusColor.backgroundTintList =  ColorStateList.valueOf(colorMap[moneyStatus[position]]?:Color.BLACK)
            }
        }

    }
}