package com.example.eatsygo_app.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eatsygo_app.model.Rating
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "clothes")
@Parcelize
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var price: Double,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var category: String,
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var rating: Rating,
    @ColumnInfo
    var isFavourite: Boolean,
    @ColumnInfo
    var isCartIn: Boolean,
    @ColumnInfo
    var itemCount: Int
): Parcelable