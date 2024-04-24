package com.example.myapplication

import MemoryManager
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class EditMemory : AppCompatActivity() {
    private lateinit var retour: ImageButton
    private lateinit var save: Button

    private lateinit var memoryManager: MemoryManager

    private lateinit var memTitleEditText: EditText
    private lateinit var memContentEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_memory)
        memoryManager =MemoryManager()

        val receivedIntent: Intent = intent
        val id: String? = receivedIntent.getStringExtra("id")


        memTitleEditText = findViewById(R.id.memTitle)
        memContentEditText = findViewById(R.id.memContent)

        if (id != null) {
            memoryManager.getTitreById(id) { titre ->
                memTitleEditText.setText(titre)       }
        }
        if (id != null) {
            memoryManager.getContenuById(id) { contenu ->
                memContentEditText.setText(contenu)
            }
        }
        memTitleEditText = findViewById(R.id.memTitle)
        memContentEditText = findViewById(R.id.memContent)
        save = findViewById(R.id.save)
        retour = findViewById(R.id.retour)

        retour.setOnClickListener {
            val intent = Intent(this, Landing::class.java)
            startActivity(intent)
        }

        save.setOnClickListener {
            val memTitle = memTitleEditText.text.toString()
            val memContent = memContentEditText.text.toString()
            if (id != null) {
                memoryManager.updateMem(id, memTitle, memContent)
            }
        }
    }
}
