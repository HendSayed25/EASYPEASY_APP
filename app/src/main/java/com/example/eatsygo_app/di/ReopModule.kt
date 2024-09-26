package com.example.eatsygo_app.di

import com.example.eatsygo_app.source.local.ClotheDao
import com.example.eatsygo_app.source.local.ClotheRepository
import com.example.eatsygo_app.source.remote.ClotheApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideClotheRepository(
        apiService: ClotheApi,
        clotheDao: ClotheDao
    ): ClotheRepository {
        return ClotheRepository(apiService, clotheDao)
    }
}
