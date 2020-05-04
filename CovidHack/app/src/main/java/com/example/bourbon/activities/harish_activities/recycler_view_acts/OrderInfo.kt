package com.example.bourbon.activities.harish_activities.recycler_view_acts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.OrderRVAdapter
import com.example.bourbon.activities.harish_activities.adapters.ShopRecyclerViewAdapter
import com.example.bourbon.activities.harish_activities.model.Order
import com.example.bourbon.databinding.RvActivityOrderInfoBinding
import print.Print

class OrderInfo : AppCompatActivity() {

    private var binding:RvActivityOrderInfoBinding?=null
    private var p: Print?=null
    private var products:ArrayList<Order>?=ArrayList()
    private var purchasedItems:ArrayList<String>?=null
    private var adapter:OrderRVAdapter?=null

    fun init(){
        p=Print(this)
        binding=DataBindingUtil.setContentView(this,R.layout.rv_activity_order_info)
        purchasedItems?.add("Hello")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        configRecyclerView()
        fetchProductsFromFirebase()
    }

    fun configRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.setLayoutManager(LinearLayoutManager(this))
        adapter = OrderRVAdapter(products)
        binding?.recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding?.recyclerView?.setAdapter(adapter)
    }

    fun fetchProductsFromFirebase(){

        for(i in 1..5){
            products?.add(Order("Harish","c007","o007",
            "12-04-2000",purchasedItems))
            adapter?.notifyItemInserted(i)
        }

    }
}
