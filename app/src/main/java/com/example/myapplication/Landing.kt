package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ActionMenuView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Landing : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var memoryRecyclerView: RecyclerView
    private lateinit var memoriesList : ArrayList<Memory>
    private lateinit var menu : ImageButton
    private lateinit var new : FloatingActionButton
    private lateinit var getM: Button

    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

       getM = findViewById<Button>(R.id.affichMem)
        getM.setOnClickListener{
            intent = Intent(this , MemoryDisplayer::class.java)
            intent.putExtra("id", "-Nw688_9RmzDG1I1h5ls")
            startActivity(intent)
        }

       menu = findViewById<ImageButton>(R.id.menu)
       new = findViewById<FloatingActionButton>(R.id.createMem)
        new.setOnClickListener() {
            val intent = Intent(this, NewMemory::class.java)
            startActivity(intent)
        }
        menu.setOnClickListener{
            Toast.makeText(this, "Unavailable for now", Toast.LENGTH_SHORT).show()
        }



        memoryRecyclerView = findViewById(R.id.memList)
        memoryRecyclerView.layoutManager = LinearLayoutManager(this)
        memoryRecyclerView.setHasFixedSize(true)

        memoriesList = arrayListOf<Memory>()
        getMemories()
    }

    private fun getMemories() {
        dbref = FirebaseDatabase.getInstance().getReference("memories")
        dbref.addValueEventListener( object  : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (memSnapshot in snapshot.children) {
                        val mem = memSnapshot.getValue(Memory::class.java)
                        memoriesList.add(mem!!)

                    }
                    memoryRecyclerView.adapter = MyAdapter(memoriesList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })}
}








