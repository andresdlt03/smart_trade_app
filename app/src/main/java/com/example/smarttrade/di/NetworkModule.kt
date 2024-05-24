package com.example.smarttrade.di

import com.example.smarttrade.BuildConfig
import com.example.smarttrade.auth.data.remote.UserApi
import com.example.smarttrade.catalogue.data.remote.CatalogueApi
import com.example.smarttrade.product_management.data.remote.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCatalogueApi(retrofit: Retrofit): CatalogueApi {
        return retrofit.create(CatalogueApi::class.java)
    }
}