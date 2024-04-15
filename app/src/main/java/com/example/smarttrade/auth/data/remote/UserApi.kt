package com.example.smarttrade.auth.data.remote

import com.example.smarttrade.auth.data.body.login.LoginCredentials
import com.example.smarttrade.auth.data.body.login.LoginResponse
import com.example.smarttrade.auth.data.body.register.RegisterResponse
import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.model.Seller
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("users/clients")
    suspend fun registerClient(@Body user: Client): Response<RegisterResponse>

    @POST("users/sellers")
    suspend fun registerSeller(@Body user: Seller): Response<RegisterResponse>

    @POST("users/login")
    suspend fun loginUser(@Body credentials: LoginCredentials): Response<LoginResponse>
}