package com.example.core.di

import com.example.core.data.interactors.ComplaintInteractors
import com.example.core.domain.repository.ComplaintRepository
import com.example.core.domain.usecase.ComplaintUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideComplaintUseCase(complaintRepository: ComplaintRepository): ComplaintUseCase {
        return ComplaintInteractors(complaintRepository)
    }
}