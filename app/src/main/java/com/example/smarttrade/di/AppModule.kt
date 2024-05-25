package com.example.smarttrade.di

import com.example.smarttrade.auth.data.remote.UserApi
import com.example.smarttrade.auth.data.repository.UserRepositoryImpl
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.catalogue.data.remote.CatalogueApi
import com.example.smarttrade.catalogue.data.repository.CatalogueRepositoryImpl
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import com.example.smarttrade.product_management.data.remote.ProductApi
import com.example.smarttrade.product_management.data.repository.ProductRepositoryImpl
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepositoryImpl(userApi, provideGsonInstance())
    }

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepositoryImpl(productApi, provideGsonInstance())
    }

    @Provides
    @Singleton
    fun provideGsonInstance(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideCatalogueRepository(catalogueApi: CatalogueApi): CatalogueRepository {
        return CatalogueRepositoryImpl(catalogueApi, provideGsonInstance())
    }
}