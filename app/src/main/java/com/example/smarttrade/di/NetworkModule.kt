package com.example.smarttrade.di

import com.example.smarttrade.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideUserApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}/auth/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}