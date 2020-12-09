package com.example.photoapiproject

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide

class SearchItemListAdapter(private val context: Activity,private val title: ArrayList<String>,private val imgid: ArrayList<String>,private val email: ArrayList<String>,private val username: ArrayList<String>,private val discription: ArrayList<String> )
    : ArrayAdapter<String>(context, R.layout.searchitemcustom_list, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.searchitemcustom_list, null, true)

        val titleText = rowView.findViewById(R.id.Name) as TextView
        val emailText = rowView.findViewById(R.id.email) as TextView
        val usernameText = rowView.findViewById(R.id.username) as TextView
        val discriptionText = rowView.findViewById(R.id.description) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView


        titleText.text = title[position]
        emailText.text = email[position]
        usernameText.text = username[position]
        discriptionText.text = discription[position]

        Glide.with(imageView.context).load(imgid[position]).into(imageView)//7389366592
        return rowView
    }


    

}