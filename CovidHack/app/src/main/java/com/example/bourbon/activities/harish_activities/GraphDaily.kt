package com.example.bourbon.activities.harish_activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bourbon.R
import com.example.bourbon.databinding.ActivityGraphDailyBinding
import org.json.JSONArray
import org.json.JSONObject
import print.Print

class GraphDaily : AppCompatActivity() {

    private var mQueue:RequestQueue?=null
    private var p: Print?=null
    private var binding:ActivityGraphDailyBinding?=null
    private var keralaDistricts:ArrayList<String>?= ArrayList()
    fun init(){
        mQueue=Volley.newRequestQueue(this)
        p=Print(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_graph_daily)
        init()


        parseGraphDailyJson("Ernakulam")
    }

    private fun parseGraphDailyJson(district:String) {
        var url: String? = "https://api.covid19india.org/districts_daily.json"

        var job:JsonObjectRequest?=JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response: JSONObject? ->

                    var districtsDaily: JSONObject? = response?.getJSONObject("districtsDaily")
                    var keralaObj:JSONObject?=districtsDaily?.getJSONObject("Kerala")

                    var arr:JSONArray=keralaObj!!.getJSONArray(district)

                    var i=0

                    while(i<arr.length()){
                        var obj:JSONObject?=arr!!.getJSONObject(i)
                        p?.sprintf("Active = ${obj?.getString("date")}")
                        i+=1
                    }


                }, Response.ErrorListener { error: VolleyError? ->
                    p!!.fprintf("Reched error ${error!!.message}")
         })

        mQueue?.add(job)
    }
}
