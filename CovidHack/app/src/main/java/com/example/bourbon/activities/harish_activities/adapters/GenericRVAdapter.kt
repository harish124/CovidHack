package com.example.bourbon.activities.harish_activities.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bourbon.R
import com.example.bourbon.activities.clement_activities.Order_Details
import com.example.bourbon.activities.harish_activities.model.CustomerOrder
import com.example.bourbon.activities.harish_activities.model.Order
import com.example.bourbon.databinding.CardCustomerOrderBinding
import com.example.bourbon.databinding.CardOrderBinding
import print.Print

class GenericRVAdapter(var products:ArrayList<CustomerOrder>, binding:CardCustomerOrderBinding): RecyclerView.Adapter<GenericRVAdapter.MyViewHolder>() {

    class MyViewHolder(itemView:CardCustomerOrderBinding,context:Context):RecyclerView.ViewHolder(itemView.root) {
        var binding:CardCustomerOrderBinding?=null

        init {
            binding=itemView


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding:CardCustomerOrderBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.card_order,
                parent, false
        )


        return MyViewHolder(binding,parent.context)
    }

    override fun getItemCount(): Int {
        return products!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder?.binding?.pd= products!!.get(position)

    }
}