package com.example.alphafire

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class userAdapter(val mctx: Context, val layoutResID: Int,val userList: List<user>)
    :ArrayAdapter<user>(mctx,layoutResID,userList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mctx)
        val view :View = layoutInflater.inflate(layoutResID,null)

        val textViewLogin =  view.findViewById<TextView>(R.id.textViewLoginID)
        val textViewPW = view.findViewById<TextView>(R.id.textViewLoginPW)
        val user = userList[position]

        textViewLogin.text = user.loginId

        textViewPW.text = user.pw

        return view

    }
}