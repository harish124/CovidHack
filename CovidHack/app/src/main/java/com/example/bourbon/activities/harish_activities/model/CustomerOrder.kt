package com.example.bourbon.activities.harish_activities.model

data class CustomerOrder(
        val custName:String,
        val custId:String,
        val orderId:String,
        val DOP:String,
        val purchasedItems:ArrayList<String>?,
        val manuel:String
)