package com.example.recyclerviewfromapi

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var myRv : RecyclerView
    var names = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRv = findViewById(R.id.recyclerView)

        doGetListResources()
    }

    fun doGetListResources(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        //progress
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        val call: Call<List<PeopleDetails.Datum>> = apiInterface!!.doGetListResources()

        call?.enqueue(object : Callback<List<PeopleDetails.Datum>> {
            override fun onResponse(
                call: Call<List<PeopleDetails.Datum>>,
                response: Response<List<PeopleDetails.Datum>>
            )
            {
                progressDialog.dismiss()
                val resource: List<PeopleDetails.Datum>? = response.body()
                for(User in resource!!) {
                    names.add(User.name.toString())
                }
                rv()
            }
            override fun onFailure(call: Call<List<PeopleDetails.Datum>>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show();
            }
        })
    }
    fun rv(){
        myRv.adapter = RecyclerViewAdapter(names)
        myRv.layoutManager = LinearLayoutManager(this)
    }
}