package com.example.photoapiproject

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_search.*
import org.json.JSONException
import java.util.*


class SearchActivity : AppCompatActivity() {


    var SRname= ArrayList<String>()
    var SRemail= ArrayList<String>()
    var SRusername= ArrayList<String>()
    var SRdiscription= ArrayList<String>()
    var SRimgurl= ArrayList<String>()

    var searchview: SearchView? = null
    private var requestQueue: RequestQueue? = null
    var pD: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        SRname.clear()
        SRemail.clear()
        SRusername.clear()
        SRdiscription.clear()
        SRimgurl.clear()

        requestQueue = Volley.newRequestQueue(this)
       searchview = findViewById(R.id.searchET) as SearchView
        pD = ProgressDialog(this)
        pD!!.setMessage("Please Wait")
        pD!!.show()
        jsonParse()


    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }



    private fun jsonParse() {

        val url = "https://mehfil-node.herokuapp.com/user/search/ayu"
        val request = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener {
                response ->try {

            for (i in 0..response.length()-1) {

                val employee = response.getJSONObject(i)
                val item = employee.getJSONObject("item")
                val name = item.getJSONObject("name")



                SRname.add(name.getString("firstName")+" "+name.getString("lastName"))
                SRemail.add(item.getString("email"))
                SRdiscription.add(item.getString("description"))
                SRimgurl.add(item.getString("profile_pic"))
                SRusername.add(item.getString("username"))

                Log.d("responseVal",response.getJSONObject(i).toString())
                Log.d("responseVal",item.toString())

            }

            //textViewResult.setText("$firstName, $imgurl, $id\n\n")

            Log.d("saveinarry",SRname.toString())
            Log.d("response",response.length().toString())


            Showsearchlist()

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }






    private fun Showsearchlist(){

        pD?.dismiss()

    val myListAdapter = SearchItemListAdapter(this,SRname,SRimgurl,SRemail,SRusername,SRdiscription)
        achievementListView.adapter = myListAdapter

    achievementListView.setOnItemClickListener(){adapterView, view, position, id ->
        val itemAtPos = adapterView.getItemAtPosition(position)
        //val itemIdAtPos = adapterView.getItemIdAtPosition(position)
        Toast.makeText(this, "Click on item at $itemAtPos", Toast.LENGTH_LONG).show()
    }


        searchET.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                if (query.toString()!=""&&!query!!.isEmpty()) {
                    val intent = Intent(applicationContext, searchmain::class.java)
                    intent.putExtra("search", query)
                    intent.putExtra("SRNAME", SRname)
                    intent.putExtra("SRIMGURL", SRimgurl)
                    intent.putExtra("SRUNAME", SRusername)
                    intent.putExtra("SRDISC", SRdiscription)
                    intent.putExtra("SREMAIL", SRemail)
                    startActivity(intent)



                }

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                //myListAdapter.getFilter().filter(newText)
                //myListAdapter.notifyDataSetChanged()
                return false
            }
        })



    }



}