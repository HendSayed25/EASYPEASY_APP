package com.example.eatsygo_app.source.remote

import com.example.eatsygo_app.model.Clothe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClotheApi {

    @GET("products")
    fun getProducts(): List<Clothe>

}