package com.example.smarttrade.auth.data.remote

import com.example.smarttrade.auth.data.body.LoginCredentials
import com.example.smarttrade.auth.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("users/{userType}s")
    suspend fun registerUser(@Body user: User, @Query("userType") userType: String): String

    @POST("users/{userType}s")
    suspend fun loginUser(@Body credentials: LoginCredentials): String
}