package com.example.adminpanel_onlinefooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanel_onlinefooddeliveryapp.Adapter.All_Item_Adapter
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminAllItemBinding
import com.example.adminpanel_onlinefooddeliveryapp.model.ItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Admin_All_Item : AppCompatActivity() {


    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private  var menuItems : ArrayList<ItemModel> = ArrayList()

    private lateinit var binding : ActivityAdminAllItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAllItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseReference = FirebaseDatabase.getInstance().reference

//        Retrving data from the database
        retriveMenuItem()
        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun retriveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")
//        fetch data from database

        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                clearing existing data before populating
                menuItems.clear()

//                loop for through each food item
                for(foodSnapShot in snapshot.children)
                {
                    val menuItem = foodSnapShot.getValue(ItemModel::class.java)

                    menuItem?.let {
                        menuItems.add(it)
                    }
                }

                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.v("Database Error",error.message)
            }
        })
    }

    private fun setAdapter() {


        val adapter = All_Item_Adapter(this@Admin_All_Item,menuItems,databaseReference)

        binding.MenuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter = adapter
    }
}