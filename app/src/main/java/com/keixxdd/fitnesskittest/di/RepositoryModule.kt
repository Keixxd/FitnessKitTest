package com.keixxdd.fitnesskittest.di

import com.keixxdd.fitnesskittest.data.repository.RepositoryImpl
import com.keixxdd.fitnesskittest.data.source.retrofit.FitnessKitApiService
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
    fun provideApplicationRepository(service: FitnessKitApiService) = RepositoryImpl(service)


}