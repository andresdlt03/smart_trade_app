package com.example.smarttrade.auth.data.remote

import com.example.smarttrade.auth.data.body.LoginCredentials
import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.model.Seller
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("users/clients")
    suspend fun registerClient(@Body user: Client): String

    @POST("users/sellers")
    suspend fun registerSeller(@Body user: Seller): String

    @POST("users/login")
    suspend fun loginUser(@Body credentials: LoginCredentials): String
}