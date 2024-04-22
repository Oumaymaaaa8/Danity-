package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ActionMenuView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)


        val menu = findViewById<ImageButton>(R.id.menu)
        val new = findViewById<FloatingActionButton>(R.id.createMem)
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








