package com.example.eatsygo_app.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eatsygo_app.model.Rating

@Entity(tableName = "clothes")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
    val isFavourite: Boolean,
    val isCartIn: Boolean
)