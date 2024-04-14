package com.example.smarttrade.auth.domain.repository

import com.example.smarttrade.auth.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User, userType: String): String
    suspend fun loginUser(email: String, password: String): String
}