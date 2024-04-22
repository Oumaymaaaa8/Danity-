package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.myapplication.databinding.ActivityLandingBinding
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title= "register"
        binding.signbut.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }

            binding.login.setOnClickListener{
                val email = binding.editTextTextEmailAddress.text.toString()
                val password = binding.editTextNumberPassword.text.toString()
                auth = FirebaseAuth.getInstance()

                if(email.isNotEmpty()&&password.isNotEmpty())
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this, Landing::class.java)
                            startActivity(intent)
                            Log.d("sign", "successful")
                            finish()
                        }
                    }.addOnFailureListener{  Log.d("sign", "leeee")}


    }}}