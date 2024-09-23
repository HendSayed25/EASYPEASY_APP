package com.example.eatsygo_app.model

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.eatsygo_app.R

data class CartItem(
    var id:Int,
    var itemCountTv: String,
    var numberOfItemNeeded: Int ,
    var title: String,
    var description:String,
    var category:String,
    var price: Double,
    var image: String,
    var rating: Rating,
    var isFavourite: Boolean,
    var isOnCart:Boolean
)