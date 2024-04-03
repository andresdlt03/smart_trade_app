package com.example.smarttrade.di

import com.example.smarttrade.auth.data.remote.UserApi
import com.example.smarttrade.auth.data.repository.UserRepositoryImpl
import com.example.smarttrade.auth.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepositoryImpl(userApi)
    }

}