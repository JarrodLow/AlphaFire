package com.example.alphafire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var  editTextID: EditText
    lateinit var  editTextpw: EditText
    lateinit var  listView: ListView

    lateinit var buttonsave: Button

    lateinit var userList : MutableList<user>

    lateinit var ref:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = mutableListOf()

         ref = FirebaseDatabase.getInstance().getReference("user")


        editTextID = findViewById(R.id.UID)
        editTextpw = findViewById(R.id.pw)
        buttonsave = findViewById(R.id.AddBt)

        listView = findViewById(R.id.userList)

        buttonsave.setOnClickListener {
            saveData()
        }

        ref.addValueEventListener(object: ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists())
                {
                    userList.clear()
                    for(h in p0.children)
                    {
                        val user = h.getValue(user::class.java)
                        userList.add(user!!)
                    }

                    val adapter = userAdapter(applicationContext, R.layout.user,userList)
                    listView.adapter = adapter

                }
            }

        })


    }



    private fun saveData()
    {
        val name = editTextID.text.toString()
        val pw = editTextpw.text.toString()

        if(name.isEmpty() )
        {
            editTextID.error = "Please enter an ID"
            return

        }
        if(pw.isEmpty())
        {
            editTextpw.error = "Please enter password"
            return
        }



        //the reference in it String is to refer what u are trying to pass for example user is USers hero is HEroes sth like that
        // basically is a nmae for database references

        //ref here can use to store values

        // UNIQUE KEY FOR THE DATA
        val AID= ref.push().key.toString()

        val Xuser = user(AID,name,pw)

        ref.child(AID).setValue(Xuser).addOnCompleteListener {
            Toast.makeText(applicationContext, "User added", Toast.LENGTH_LONG).show()
        }
    }

}
