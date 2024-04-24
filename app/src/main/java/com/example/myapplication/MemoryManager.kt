import android.content.Intent
import android.widget.Toast
import com.example.myapplication.Memory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Calendar

class MemoryManager {

    private lateinit var dbref : DatabaseReference

    fun createMem(title: String, content: String) {
        dbref = FirebaseDatabase.getInstance().getReference("memories")
        val date = Calendar.getInstance().time
        val memId = dbref.push().key!!
        val memory = Memory(memId, title, content, date)
        dbref.child(memId).setValue(memory).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun deleteMemoryById(id: String) {
        dbref = FirebaseDatabase.getInstance().getReference("memories")
        dbref.child(id).removeValue()
    }

    fun getTitreById(id: String, callback: (String) -> Unit) {
        dbref = FirebaseDatabase.getInstance().getReference("memories")
        dbref.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val memory = dataSnapshot.getValue(Memory::class.java)
                val titre = memory?.title ?: ""
                callback(titre)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
    }

    fun getContenuById(id: String, callback: (String) -> Unit) {
        dbref = FirebaseDatabase.getInstance().getReference("memories")
        dbref.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val memory = dataSnapshot.getValue(Memory::class.java)
                val contenu = memory?.content ?: ""
                callback(contenu)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
    }

    fun updateMem(id: String, title: String, content: String) {
        dbref = FirebaseDatabase.getInstance().getReference("memories")
        deleteMemoryById(id)
        val date = Calendar.getInstance().time
        val memory = Memory(id, title, content, date)
        dbref.child(id).setValue(memory).addOnCompleteListener {
            // Handle completion if needed
        }.addOnFailureListener {
            // Handle failure if needed
        }
    }
}
