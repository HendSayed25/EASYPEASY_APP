package com.example.eatsygo_app.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eatsygo_app.model.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ClotheDatabase : RoomDatabase() {
    abstract fun productDao(): ClotheDao
}