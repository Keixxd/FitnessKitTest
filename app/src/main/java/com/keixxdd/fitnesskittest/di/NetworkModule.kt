package com.keixxdd.fitnesskittest.di

import com.google.gson.GsonBuilder
import com.keixxdd.fitnesskittest.data.source.retrofit.FitnessKitApiService
import com.keixxdd.fitnesskittest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): FitnessKitApiService =
        Retrofit.Builder()
            .baseUrl(Constants.apiUrl)
            .client(createHttpClient())
            .addConverterFactory(createGsonConverter())
            .build()
            .create(FitnessKitApiService::class.java)


    private fun createHttpClient() : OkHttpClient = with(OkHttpClient.Builder()) {
        connectTimeout(30L, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        build()
    }

    private fun createGsonConverter() = GsonConverterFactory.create(GsonBuilder().create())
}