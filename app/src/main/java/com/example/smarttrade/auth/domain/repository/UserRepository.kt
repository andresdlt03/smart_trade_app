package com.example.smarttrade.auth.domain.repository

import com.example.smarttrade.auth.data.body.login.LoginResponse
import com.example.smarttrade.auth.data.body.register.RegisterResponse
import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.model.Seller
import retrofit2.Response

interface UserRepository {
    suspend fun registerClient(user: Client): Response<RegisterResponse>
    suspend fun registerSeller(user: Seller): Response<RegisterResponse>
    suspend fun loginUser(email: String, password: String): Response<LoginResponse>
}