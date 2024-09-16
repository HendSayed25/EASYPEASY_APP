package com.example.eatsygo_app.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@field:SerializedName("ApiResponse")
	val apiResponse: List<ApiResponseItem?>? = null
)

data class RatingAll(

	@field:SerializedName("rate")
	val rate: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

data class ApiResponseItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: Any? = null,

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
