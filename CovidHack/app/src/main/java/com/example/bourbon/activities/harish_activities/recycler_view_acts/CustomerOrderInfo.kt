package com.example.bourbon.activities.harish_activities.recycler_view_acts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.clement_activities.model.User
import com.example.bourbon.activities.harish_activities.adapters.GenericRVAdapter
import com.example.bourbon.activities.harish_activities.model.CustomerOrder
import com.example.bourbon.databinding.RvActivityOrderInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import print.Print

class CustomerOrderInfo : AppCompatActivity() {

        private var binding: RvActivityOrderInfoBinding?=null
        private val p=Print(this)
        private var products= arrayListOf<CustomerOrder>()

        private val adapter=GenericRVAdapter(products)
        private val mDatabase= FirebaseDatabase.getInstance().reference
        private val auth= FirebaseAuth.getInstance()
        private var user= auth.currentUser?: User("SomethingWentWrong","")

        fun init(){
            binding= DataBindingUtil.setContentView(this, R.layout.rv_customer_order_info)

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

            binding?.recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            binding?.recyclerView?.setAdapter(adapter)
        }

        fun fetchProductsFromFirebase() {
            //fill this
        }
}