package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.util.Calendar


class NewMemory : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference

    private lateinit var memTitle : EditText
    private lateinit var memContent : EditText
    private lateinit var save : Button
    private lateinit var retour : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_memory)
        memTitle = findViewById(R.id.memTitle)
        memContent = findViewById(R.id.memContent)
        save = findViewById(R.id.save)
        retour = findViewById(R.id.retour)
        dbref = FirebaseDatabase.getInstance().getReference("memories")

        retour.setOnClickListener{
             saveMemory()
             val intent = Intent(this , Landing ::class.java)
             startActivity(intent)
        }
        save.setOnClickListener {
        saveMemory()
        }
    }
    private fun saveMemory(){

        val title = memTitle.text.toString()
        val content = memContent.text.toString()
        val date = Calendar.getInstance().time

        if(title.isEmpty()){ memTitle.error = "empty title" }
        if(content.isEmpty()){ memContent.error = "empty title" }
        val memId = dbref.push().key!!
        val memory =Memory(memId, title,content , date)
        dbref.child(memId).setValue(memory).addOnCompleteListener{
Toast.makeText(this, "memory successfully inserted",Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this, "memory NOT inserted",Toast.LENGTH_LONG).show()

        }}
}


