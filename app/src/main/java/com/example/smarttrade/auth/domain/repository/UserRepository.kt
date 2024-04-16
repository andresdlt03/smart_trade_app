package com.example.smarttrade.auth.domain.repository

import com.example.smarttrade.auth.domain.model.User
import retrofit2.Response

interface UserRepository {
    suspend fun registerUser(user: User, userType: String): Response<String>
    suspend fun loginUser(email: String, password: String): Response<String>
}