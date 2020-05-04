package com.example.bourbon.activities.harish_activities.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.model.Order
import com.example.bourbon.databinding.CardOrderBinding

import print.Print

class OrderRVAdapter(var products:ArrayList<Order>?):RecyclerView.Adapter<OrderRVAdapter.MyViewHolder>() {
    var p:Print?=null
    class MyViewHolder(itemView:CardOrderBinding):RecyclerView.ViewHolder(itemView.root) {
        var binding:CardOrderBinding?=null

        init {
            binding=itemView

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding:CardOrderBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.card_order,
                parent, false
        )
        p = Print(parent.context)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder?.binding?.pd= products!!.get(position)
    }
}