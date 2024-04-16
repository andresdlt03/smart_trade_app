package com.example.smarttrade.auth.data.remote

import com.example.smarttrade.auth.http.login.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("users/{userType}s")
    suspend fun registerUser(@Body user: String, @Path("userType") userType: String): Response<String>

    @POST("users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<String>
}