package com.example.adminpanel_onlinefooddeliveryapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminAddItemBinding
import com.example.adminpanel_onlinefooddeliveryapp.model.ItemModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class Admin_Add_Item : AppCompatActivity() {
    private lateinit var binding: ActivityAdminAddItemBinding
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private lateinit var foodDescription: String
    private lateinit var foodIngredients: String
    private var foodImageUri: Uri? = null

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addItemButton.setOnClickListener {


            foodName = binding.enterFoodName.text.toString()
            foodPrice = binding.enterFoodPrice.text.toString()
            foodDescription = binding.description.text.toString()
            foodIngredients = binding.ingredients.text.toString()

            if (foodName.isNotBlank() && foodIngredients.isNotBlank() && foodDescription.isNotBlank() && foodPrice.isNotBlank()) {
               Log.v("Before Going into Upload Function",foodName)
                Log.v("Before Going into Upload Function",foodPrice)
                Log.v("Before Going into Upload Function",foodDescription)

                uploadData()
                Log.v("Before Going into Upload Function",foodIngredients)
                finish()
            } else {
                Toast.makeText(this, "Fill All details", Toast.LENGTH_SHORT).show()
            }
        }

        binding.selectImage.setOnClickListener {
            pickImage.launch("image/*")
        }
    }

    private fun uploadData() {
        val menuRef = database.getReference("menu")
        Log.v("menu Reference is: ", menuRef.toString())
        val newItemKey = menuRef.push().key
        Log.v("new Item Key is: ", newItemKey.toString())

        if (foodImageUri != null) {
            Log.v("Image URI is: ", foodImageUri.toString())
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_images/${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(foodImageUri!!)

            Log.v("Before uploading image to: ", imageRef.toString())
            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    // Create New Menu Item
                    Log.v("Download URL: ", downloadUrl.toString())
                    val newItem = ItemModel(
                        foodName = foodName,
                        foodPrice = foodPrice,
                        foodDescription = foodDescription,
                        foodIngredients = foodIngredients,
                        foodImage = downloadUrl.toString()
                    )

                    newItemKey?.let { key ->
                        menuRef.child(key).setValue(newItem).addOnSuccessListener {
                            Log.v("Firebase", "Item Added Successfully")
                            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener { e ->
                            Log.e("Firebase", "Menu child not created: ${e.message}")
                            Toast.makeText(this, "Data Upload Failed: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }.addOnFailureListener { e ->
                    Log.e("Firebase", "Failed to get download URL: ${e.message}")
                    Toast.makeText(this, "Failed to get download URL: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener { e ->
                Log.e("Firebase", "Image Upload Failed: ${e.message}")
                Toast.makeText(this, "Image Upload Failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please Select the Image", Toast.LENGTH_SHORT).show()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            binding.selectedImage.setImageURI(uri)
            foodImageUri = uri
        }
    }
}
