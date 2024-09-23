package com.example.eatsygo_app.source.remote

import com.example.eatsygo_app.model.Clothe
import retrofit2.Call
import retrofit2.http.GET

interface ClotheApi {

    @GET("products")
    suspend fun getProducts(): List<Clothe>



}