package com.example.photoapiproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertView
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.achievementListView
import kotlinx.android.synthetic.main.activity_searchmain.*
import java.util.ArrayList
import kotlin.math.log

class searchmain : AppCompatActivity() {

    var SRname= ArrayList<String>()
    var SRemail= ArrayList<String>()
    var SRusername= ArrayList<String>()
    var SRdiscription= ArrayList<String>()
    var SRimgurl= ArrayList<String>()

    var CSRname= ArrayList<String>()
    var CSRemail= ArrayList<String>()
    var CSRusername= ArrayList<String>()
    var CSRdiscription= ArrayList<String>()
    var CSRimgurl= ArrayList<String>()



    var FINDINDEX= ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchmain)

        SRname.clear()
        SRemail.clear()
        SRimgurl.clear()
        SRdiscription.clear()
        SRusername.clear()

        CSRusername.clear()
        CSRname.clear()
        CSRemail.clear()
        CSRdiscription.clear()
        CSRimgurl.clear()

        val alert = AlertView("ALERT!!!!", "Item not exist Please Modify your search", AlertStyle.DIALOG)


        var search=intent.getStringExtra("search")
        SRname = intent.getStringArrayListExtra("SRNAME")!!
        SRemail = intent.getStringArrayListExtra("SREMAIL")!!
        SRusername = intent.getStringArrayListExtra("SRUNAME")!!
        SRdiscription = intent.getStringArrayListExtra("SRDISC")!!
        SRimgurl = intent.getStringArrayListExtra("SRIMGURL")!!



            //Toast.makeText(this,"${search} is Searching....",Toast.LENGTH_SHORT).show()
        //Toast.makeText(this,SRname.size.toString(),Toast.LENGTH_SHORT).show()


        for(i in 0..SRname.size-1){

            if (SRname[i].toLowerCase().contains(search.toString().toLowerCase())){

                FINDINDEX.add(i)
                Toast.makeText(this,"Result found",Toast.LENGTH_SHORT).show()
                Log.d("foundedindex",SRname[i])
            }
            else if (SRusername[i].toLowerCase().contains(search.toString().toLowerCase())){
                FINDINDEX.add(i)
                Toast.makeText(this,"Result found",Toast.LENGTH_SHORT).show()
                Log.d("foundedindex",SRusername[i])
            }
            else if (SRemail[i].toLowerCase().contains(search.toString().toLowerCase())){
                FINDINDEX.add(i)
                Toast.makeText(this,"Result found",Toast.LENGTH_SHORT).show()
                Log.d("foundedindex",SRemail[i])

            }

        }


        if (FINDINDEX.size==0){
            alert.show(this)
        }
        else{

            Log.d("foundedindex",FINDINDEX.toString())

            for (i in FINDINDEX){
                CSRname.add(SRname[i])
                CSRemail.add(SRemail[i])
                CSRdiscription.add(SRdiscription[i])
                CSRusername.add(SRusername[i])
                CSRimgurl.add(SRimgurl[i])

            }


            val myListAdapter = SearchItemListAdapter(this,CSRname,CSRimgurl,CSRemail,CSRusername,CSRdiscription)
            achievementListView.adapter = myListAdapter

            achievementListView.setOnItemClickListener(){adapterView, view, position, id ->
                val itemAtPos = adapterView.getItemAtPosition(position)
                //val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                Toast.makeText(this, "Click on item at $itemAtPos ", Toast.LENGTH_LONG).show()
            }


        }

        backarrow.setOnClickListener {

            finish()
        }

    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}