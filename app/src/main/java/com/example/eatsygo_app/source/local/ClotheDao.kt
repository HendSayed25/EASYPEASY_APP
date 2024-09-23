package com.example.eatsygo_app.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eatsygo_app.model.entity.ProductEntity

@Dao
interface ClotheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM clothes")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM clothes WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity

    @Query("SELECT * FROM clothes WHERE category = :category")
    suspend fun getProductByCategory(category: String): List<ProductEntity>

    @Query("SELECT * FROM clothes WHERE isFavourite =1")
    suspend fun getFavouriteProduct(): List<ProductEntity>

    @Query("SELECT * FROM clothes WHERE isCartIn = 1")
    suspend fun getCartProduct(): List<ProductEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(productItem:ProductEntity):Int
}