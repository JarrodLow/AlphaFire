package com.example.alphafire

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView

class userAdapter(val mctx: Context, val layoutResID: Int,val userList: List<user>)
    :ArrayAdapter<user>(mctx,layoutResID,userList){

    //here is retrieve
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mctx)
        val view :View = layoutInflater.inflate(layoutResID,null)

        val textViewLogin =  view.findViewById<TextView>(R.id.textViewLoginID)
        val textViewPW = view.findViewById<TextView>(R.id.textViewLoginPW)

        val textViewLoginUpdate = view.findViewById<TextView>(R.id.Update)

        val user = userList[position]

        textViewLogin.text = user.loginId

        textViewPW.text = user.pw

        textViewLoginUpdate.setOnClickListener{
            showUpdateDialog()
        }

        return view

    }
    //
    fun showUpdateDialog(){
        val builder = AlertDialog.Builder(mctx)

        val inflater = LayoutInflater.from(mctx)

        val view = inflater.inflate(R.layout.updateuser,null)

        val editText = view.findViewById<EditText>(R.id.UID)
        val editPw = view.findViewById<EditText>(R.id.pw)

        builder.setView(view)

        builder.setPositiveButton("Update"
        ) { p0, p1 ->

        }

        builder.setNegativeButton("No"
        ) { p0, p1 ->

        }

        val alert = builder.create()
        alert.show()
    }
}