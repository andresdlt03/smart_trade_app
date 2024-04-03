package com.example.smarttrade.auth.domain.repository

import com.example.smarttrade.auth.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}