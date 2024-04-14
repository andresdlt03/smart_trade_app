package com.example.smarttrade.auth.domain.repository

import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.model.Seller

interface UserRepository {
    suspend fun registerClient(user: Client): String
    suspend fun registerSeller(user: Seller): String
    suspend fun loginUser(email: String, password: String): String
}