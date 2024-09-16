package com.example.eatsygo_app.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eatsygo_app.model.RateConverter
import com.example.eatsygo_app.model.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(RateConverter::class)
abstract class ClotheDatabase : RoomDatabase() {
    abstract fun productDao(): ClotheDao


    companion object {
        @Volatile
        private var INSTANCE: ClotheDatabase? = null

        fun getInstance(context: Context): ClotheDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClotheDatabase::class.java,
                    "clothe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
