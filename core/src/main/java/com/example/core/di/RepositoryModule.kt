package com.example.core.di

import com.example.core.data.repository.ComplaintRepositoryImpl
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.repository.ComplaintRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideComplaintRepository(apiService: ApiService): ComplaintRepository {
        return ComplaintRepositoryImpl(apiService)
    }
}