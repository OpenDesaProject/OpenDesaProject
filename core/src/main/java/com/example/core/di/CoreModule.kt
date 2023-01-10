package com.example.core.di

import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(remoteDataSource: RemoteDataSource) : ApiService {
        return remoteDataSource.buildApi(ApiService::class.java)
    }
}