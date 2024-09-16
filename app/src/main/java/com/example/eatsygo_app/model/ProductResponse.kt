package com.example.eatsygo_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("ProductResponse")
	val productResponse: List<ProductResponseItem?>? = null
)

data class Rating(

	@field:SerializedName("rate")
	val rate: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null
)
@Entity
data class ProductResponseItem(

	@ColumnInfo
	val isFavourite: Boolean?=null,
	@ColumnInfo
	val isCartIn: Boolean,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null


)
