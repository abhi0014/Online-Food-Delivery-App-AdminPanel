package com.example.adminpanel_onlinefooddeliveryapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.adminpanel_onlinefooddeliveryapp.databinding.PendingOrderItemBinding

class PendingOrderAdapter(private val nameOfCustomer : ArrayList<String>,private val quantity:ArrayList<String>,private val foodImage : ArrayList<Int>): RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        val binding = PendingOrderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PendingOrderViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class PendingOrderViewHolder(private val binding: PendingOrderItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                binding.customerName.text = nameOfCustomer[position]
                binding.foodQuantity.text = quantity[position]
                binding.menuFoodImageView.setImageResource(foodImage[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return nameOfCustomer.size
    }

}