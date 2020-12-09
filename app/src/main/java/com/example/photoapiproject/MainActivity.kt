package com.example.photoapiproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    var nameAL = ArrayList<String>()
    var ImgUrlAL = ArrayList<String>()
    var idAl = ArrayList<String>()


    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)
            jsonParse()


        ivNavMenu.setOnClickListener {
            toggleLeftDrawer()
        }


        msearch_bar.setOnClickListener {

            startActivity(Intent(this,SearchActivity::class.java))
        }

    }



    private fun toggleLeftDrawer() {
        if (drawerLayout.isDrawerOpen(leftDrawerMenu)) {
            drawerLayout.closeDrawer(leftDrawerMenu)
        } else {
            drawerLayout.openDrawer(leftDrawerMenu)
        }
    }


    private fun jsonParse() {

        val url = "https://picsum.photos/v2/list"
        val request = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener {
            response ->try {
            var firstName=""
            var imgurl=""
            var id=""
            for (i in 0..response.length()-1) {

                val employee = response.getJSONObject(i)
                firstName = employee.getString("author")
                imgurl = employee.getString("download_url")
                id = employee.getString("id")

                nameAL.add(firstName)
                ImgUrlAL.add(imgurl)
                idAl.add(id)

                Log.d("responseVal",response.getJSONObject(i).toString())

                }

            //textViewResult.setText("$firstName, $imgurl, $id\n\n")
            Log.d("response",response.length().toString())
            Log.d("response",nameAL.size.toString())

            RVresultShow()

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }




    private fun RVresultShow() {

            Log.d("RV img", "initRecyclerView: init recyclerview")

            val layoutManager =  GridLayoutManager(this, 3)
            val recyclerView: RecyclerView = CatrecyclerView
            recyclerView.layoutManager = layoutManager
            val adapter = ImageRecyclerViewAdapter(
                this,
                nameAL,
                ImgUrlAL
            )
            recyclerView.adapter = adapter
    }







}