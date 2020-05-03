package com.example.bourbon.activities.harish_activities.recycler_view_acts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.CovidStatusRecyclerViewAdapter
import com.example.bourbon.activities.harish_activities.adapters.ShopRecyclerViewAdapter
import com.example.bourbon.activities.harish_activities.model.Shop
import com.example.bourbon.databinding.RvActivityShopInfoBinding
import print.Print

class ShopInfo : AppCompatActivity() {

    private var binding:RvActivityShopInfoBinding?=null
    private var p: Print?=null
    private var products:ArrayList<Shop>?=null
    private var adapter:ShopRecyclerViewAdapter?=null

     fun init(){
        binding =DataBindingUtil.setContentView(this,R.layout.rv_activity_shop_info)
        p=Print(this)
        products=ArrayList()
    }

    fun configRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.setLayoutManager(LinearLayoutManager(this))
        adapter =ShopRecyclerViewAdapter(products)
        binding?.recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding?.recyclerView?.setAdapter(adapter)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        configRecyclerView()
        fetchProductsFromFirebase()
    }

    fun fetchProductsFromFirebase(){
        for(i in 1..5){
            products?.add(Shop("chennai $i","addr$i","stationary $i"))
            adapter?.notifyItemInserted(i)
        }

    }
}
