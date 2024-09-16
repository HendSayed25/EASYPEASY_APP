
package com.example.eatsygo_app.di

import android.content.Context
import androidx.room.Room
import com.example.eatsygo_app.source.local.ClotheDatabase
import com.example.eatsygo_app.source.local.ClotheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideClotheDatabase(@ApplicationContext context: Context): ClotheDatabase {
        return ClotheDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideClotheDao(database: ClotheDatabase): ClotheDao {
        return database.productDao()
    }
}


