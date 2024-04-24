package com.example.myapplication

import MemoryManager
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

class MemoryDisplayer : AppCompatActivity() {
    private lateinit var retour : ImageButton
    private lateinit var delete : ImageButton
    private lateinit var edit : ImageButton
    private  lateinit var memoryManager : MemoryManager

    private lateinit var titreView: TextView
    private lateinit var contentView: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_displayer)
        retour = findViewById(R.id.retour)
        edit = findViewById(R.id.edit)
        delete = findViewById(R.id.delete)
        val receivedIntent: Intent = intent
        val id: String= receivedIntent.getStringExtra("id").toString()
        memoryManager = MemoryManager()

        titreView = findViewById(R.id.memTitle)
        contentView = findViewById(R.id.memContent)

            memoryManager.getTitreById(id) { titre ->
                titreView.text =titre
            }


            memoryManager.getContenuById(id) { contenu ->
                contentView.text = contenu
            }



        retour.setOnClickListener{
            val intent = Intent(this , Landing ::class.java)
            startActivity(intent)
        }
        edit.setOnClickListener {
            intent =Intent(this , EditMemory::class.java)
            intent.putExtra("id" ,id)
            startActivity(intent)
        }
        delete.setOnClickListener {
            memoryManager.deleteMemoryById(id)
            intent =Intent(this , Landing::class.java)
            startActivity(intent)
          }


    }
}