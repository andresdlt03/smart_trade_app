package com.example.smarttrade.auth.domain.repository

import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.model.Seller
import retrofit2.Response

interface UserRepository {
    suspend fun registerClient(user: Client): Response<String>
    suspend fun registerSeller(user: Seller): Response<String>
    suspend fun loginUser(email: String, password: String): Response<String>
}