package com.example.eatsygo_app.model

import android.widget.ImageView
import android.widget.TextView
import com.example.eatsygo_app.R

data class CartItem(
    var itemImage: Int,
    var itemName: String,
    var itemCountTv: String,
    var numberOfItemNeeded: String ,
    var itemPrice: String,
)