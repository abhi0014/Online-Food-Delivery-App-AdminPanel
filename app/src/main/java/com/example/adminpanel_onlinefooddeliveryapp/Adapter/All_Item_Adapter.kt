package com.example.adminpanel_onlinefooddeliveryapp.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ItemMenuBinding
import com.example.adminpanel_onlinefooddeliveryapp.model.ItemModel
import com.google.firebase.database.DatabaseReference

class All_Item_Adapter(
    private val context: Context,
    private val menuList: ArrayList<ItemModel>,
    databaseReference: DatabaseReference
):RecyclerView.Adapter<All_Item_Adapter.AddAllItemViewHolder>() {

    private val itemQuantities = IntArray(menuList.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAllItemViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddAllItemViewHolder(binding)
    }



    override fun onBindViewHolder(holder: AddAllItemViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int {
        return menuList.size
    }
    inner class AddAllItemViewHolder(private val binding: ItemMenuBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.apply {
                val qunatity = itemQuantities[position]

                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)


                menuFoodName.text = menuItem.foodName
                menuFoodPrice.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(menuFoodImageView)
                qunatityTextView.text=qunatity.toString()
                minusButton.setOnClickListener {
                    decrease(position)
                }
                plusButton.setOnClickListener {
                    increase(position)
                }

                deleteButton.setOnClickListener {
                    deleteItem(position)
                }


            }
        }

        private fun deleteItem(position: Int) {
           menuList.removeAt(position)
            menuList.removeAt(position)
            menuList.removeAt(position)

            notifyItemRemoved(position)
            notifyItemRangeChanged(position,menuList.size)
        }

        private fun increase(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++;
                binding.qunatityTextView.text = itemQuantities[position].toString()
            }
        }

        private fun decrease(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.qunatityTextView.text = itemQuantities[position].toString()
            }

        }
    }


}