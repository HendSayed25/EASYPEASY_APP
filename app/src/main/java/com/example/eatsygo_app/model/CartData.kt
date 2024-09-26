package com.example.eatsygo_app.model

data class CartData(
    val id: Int,
    val image: String,
    val title: String,
    val price: Double,
    var itemCount: Int,
    var isCartIn: Boolean
)
