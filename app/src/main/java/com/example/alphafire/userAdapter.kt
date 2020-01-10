package com.example.alphafire

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class userAdapter(val mctx: Context, val layoutResID: Int,val userList: List<user>)
    :ArrayAdapter<user>(mctx,layoutResID,userList){

    //here is retrieve
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mctx)
        val view :View = layoutInflater.inflate(layoutResID,null)

        val textViewLogin =  view.findViewById<TextView>(R.id.textViewLoginID)
        val textViewPW = view.findViewById<TextView>(R.id.textViewLoginPW)
        val textViewTest = view.findViewById<TextView>(R.id.testTrue)


        val tester:String = "asd"
        var holder:Boolean = true

        val holder1:String


        //here is the update de part
        val textViewLoginUpdate = view.findViewById<TextView>(R.id.Update)

        val user = userList[position]


        textViewLogin.text = user.loginId

        textViewPW.text = user.pw

        if(user.pw.equals(tester))
        {
            holder = true
            textViewTest.text = holder.toString()
        }
        else
        {
            holder = false
            textViewTest.text = holder.toString()
        }

        textViewLoginUpdate.setOnClickListener{
            showUpdateDialog(user)
        }

        return view

    }
    //
    fun showUpdateDialog(user: user) {
        val builder = AlertDialog.Builder(mctx)
        builder.setTitle("Update User")
        val inflater = LayoutInflater.from(mctx)

        val view = inflater.inflate(R.layout.updateuser,null)

        val editText = view.findViewById<EditText>(R.id.UID)
        val editPw = view.findViewById<EditText>(R.id.pw)

        editText.setText(user.loginId)
        editPw.setText(user.pw)

        builder.setView(view)

        builder.setPositiveButton("Update"
        ) { p0, p1 ->
            val dbuser = FirebaseDatabase.getInstance().getReference("users")

            val  Uid = editText.text.toString().trim()
            val  pass = editPw.text.toString().trim()


            if(Uid.isEmpty())
            {
                editText.error = "Please enter ID"
                editText.requestFocus()
                return@setPositiveButton

            }

            if(pass.isEmpty())
            {
                editPw.error = "Please enter password"
                editPw.requestFocus()
                return@setPositiveButton
            }

            val user = user(user.id,Uid,pass)
            dbuser.child(user.id).setValue(user)

            Toast.makeText(mctx,"User updated",Toast.LENGTH_LONG).show()



        }

        builder.setNegativeButton("No"
        ) { p0, p1 ->

        }

        val alert = builder.create()
        alert.show()
    }
}