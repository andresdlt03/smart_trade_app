package com.example.smarttrade.auth.data.remote

import com.example.smarttrade.auth.domain.model.Seller
import com.example.smarttrade.auth.http.login.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("users/clients")
    suspend fun registerClient(@Body user: String): Response<String>

    @POST("users/sellers")
    suspend fun registerSeller(@Body user: Seller): Response<String>

    @POST("users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<String>
}