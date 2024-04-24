package com.example.myapplication

import MemoryManager
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


    private lateinit var memoryManager: MemoryManager
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
        memoryManager = MemoryManager()

        retour.setOnClickListener{
             val intent = Intent(this , Landing ::class.java)
             startActivity(intent)
        }
            save.setOnClickListener {
            memoryManager.createMem(memTitle.text.toString(), memContent.text.toString())
        }
    }

}


