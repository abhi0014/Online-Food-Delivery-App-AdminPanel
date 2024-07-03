package com.example.adminpanel_onlinefooddeliveryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityAdminSignUpBinding
import com.example.adminpanel_onlinefooddeliveryapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Admin_SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var nameOfRestraunt: String
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityAdminSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        firebase Intialization auth


        auth = Firebase.auth
        database = Firebase.database.reference


        binding.createAccountButton.setOnClickListener {

            email = binding.adminEmail.text.toString().trim()
            password = binding.adminPassword.text.toString().trim()
            userName = binding.name.text.toString()
            nameOfRestraunt = binding.restrauntName.text.toString()

            if(userName.isBlank() || email.isBlank() || password.isBlank()||nameOfRestraunt.isBlank())
            {
                Toast.makeText(this,"Please Fill all details", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }

        }


        val locationList = arrayOf("Ratlam", "Indore", "Bhopal", "Mandsaur")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locationList)

        val autoCompleteTextView = binding.ListOfLocation
        autoCompleteTextView.setAdapter(adapter)

        binding.alreadyHaveAccount.setOnClickListener {
            val intent = Intent(this, Admin_Login::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this,"User Successfully Created",Toast.LENGTH_SHORT).show()
//                Saving data in database
                saveUserData()
                val intent = Intent(this, Admin_Login::class.java)
                startActivity(intent)

                finish()

            }
            else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount : Failure",task.exception)
            }
        }
    }

    private fun saveUserData() {
        email = binding.adminEmail.text.toString().trim()
        password = binding.adminPassword.text.toString().trim()
        userName = binding.name.text.toString()
        nameOfRestraunt = binding.restrauntName.text.toString()
         val user = UserModel(userName,nameOfRestraunt,email,password)


        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("admin").child(userId).setValue(user)
        val databaseRef = database.child("user").ref
        Log.v("User database ref ",databaseRef.toString())
    }
}