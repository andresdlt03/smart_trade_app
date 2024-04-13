package com.example.smarttrade.auth.data.repository

import com.example.smarttrade.auth.data.body.LoginCredentials
import com.example.smarttrade.auth.data.remote.UserApi
import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.auth.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun registerUser(user: User, userType: String): String {
        try {
            return userApi.registerUser(user, userType)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun loginUser(email: String, password: String): String {
        try {
            val credentials = LoginCredentials(email, password)
            return userApi.loginUser(credentials)
        } catch (e: Exception) {
            throw e
        }
    }
}