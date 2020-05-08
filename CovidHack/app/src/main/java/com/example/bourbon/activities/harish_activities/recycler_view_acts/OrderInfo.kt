package com.example.bourbon.activities.harish_activities.recycler_view_acts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.OrderRVAdapter
import com.example.bourbon.activities.harish_activities.adapters.ShopRecyclerViewAdapter
import com.example.bourbon.activities.harish_activities.model.Order
import com.example.bourbon.activities.harish_activities.model.Shop
import com.example.bourbon.databinding.RvActivityOrderInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import print.Print

class OrderInfo : AppCompatActivity() {

    private var binding:RvActivityOrderInfoBinding?=null
    private var p: Print?=null
    private var products:ArrayList<Order>?=ArrayList()

    private var adapter:OrderRVAdapter?=null
    private var mDatabase: DatabaseReference?=null
    private var auth: FirebaseAuth?=null
    private var user: FirebaseUser?=null

    fun init(){
        p=Print(this)
        binding=DataBindingUtil.setContentView(this,R.layout.rv_activity_order_info)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        configRecyclerView()
        mDatabase = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser
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


        mDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // ...
                var i:Int=0
                for (postSnapshot in dataSnapshot.child("Carts").children) {
                    var purchasedItems:ArrayList<String>?=null
                    // TODO: handle the post
                    if(postSnapshot.child("shopId").value == user?.uid){


                        var custId:String = postSnapshot.child("customerId").value.toString()
                        var dop:String = postSnapshot.key.toString()
                        var shopId:String = postSnapshot.child("shopId").value.toString()
                        var manuel:String = postSnapshot.child("manuel").value.toString()
                        var custName:String = dataSnapshot.child("users").child(custId).child("Name").value.toString()

                        if(manuel == "False"){
                            for(items in postSnapshot.child(dop).child("Items").children){
                                purchasedItems?.add(items.value.toString())
                            }
                            products?.add(Order(custName,custId,"7",dop,purchasedItems,manuel))
                            adapter?.notifyItemInserted(i)
                            i++
                        }else{
                            purchasedItems?.add("NULL")
                            products?.add(Order(custName,custId,"o007",
            dop,purchasedItems,manuel))
            adapter?.notifyItemInserted(i)
                        }


                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                val toast = Toast.makeText(applicationContext, databaseError.message, Toast.LENGTH_LONG)
                toast.show()

                // ...
            }
        })




//        for(i in 1..5){
//            products?.add(Order("Harish","c007","o007",
//            "12-04-2000",purchasedItems))
//            adapter?.notifyItemInserted(i)
//        }

    }
}
