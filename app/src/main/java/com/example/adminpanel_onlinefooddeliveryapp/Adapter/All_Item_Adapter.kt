package com.example.adminpanel_onlinefooddeliveryapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminAllItemBinding
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ItemMenuBinding

class All_Item_Adapter(private val MenuItemName : ArrayList<String>,private val MenuItemPrice:ArrayList<String>,private val MenuItemImage:ArrayList<Int>):RecyclerView.Adapter<All_Item_Adapter.AddAllItemViewHolder>() {

    private val itemQuantities = IntArray(MenuItemName.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAllItemViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddAllItemViewHolder(binding)
    }



    override fun onBindViewHolder(holder: AddAllItemViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int {
        return MenuItemName.size
    }
    inner class AddAllItemViewHolder(private val binding: ItemMenuBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.apply {
                val qunatity = itemQuantities[position]
                menuFoodName.text = MenuItemName[position]
                menuFoodPrice.text = MenuItemPrice[position]
                menuFoodImageView.setImageResource(MenuItemImage[position])
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
           MenuItemName.removeAt(position)
            MenuItemPrice.removeAt(position)
            MenuItemImage.removeAt(position)

            notifyItemRemoved(position)
            notifyItemRangeChanged(position,MenuItemImage.size)
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