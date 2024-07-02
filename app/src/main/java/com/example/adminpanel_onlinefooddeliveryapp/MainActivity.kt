package com.example.adminpanel_onlinefooddeliveryapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanel_onlinefooddeliveryapp.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize GoogleSignInClient
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.AddItemMenu.setOnClickListener {
            val intent = Intent(this, Admin_All_Item::class.java)
            startActivity(intent)
        }

        binding.AddMenu.setOnClickListener {
            val intent = Intent(this, Admin_Add_Item::class.java)
            startActivity(intent)
        }

        binding.OrderDispatch.setOnClickListener {
            val intent = Intent(this, AdminOutForDelivery::class.java)
            startActivity(intent)
        }

        binding.Profile.setOnClickListener {
            val intent = Intent(this, AdminProfile::class.java)
            startActivity(intent)
        }

        binding.CreateNewUser.setOnClickListener {
            val intent = Intent(this, CreateNewUser::class.java)
            startActivity(intent)
        }

        binding.checkPendingOrder.setOnClickListener {
            val intent = Intent(this, PendingOrder::class.java)
            startActivity(intent)
        }

        binding.Logout.setOnClickListener {
            // Sign out from FirebaseAuth
            Firebase.auth.signOut()

            // Sign out from Google
            googleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, Admin_Login::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
