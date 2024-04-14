package com.example.smarttrade.auth.data.repository

import com.example.smarttrade.auth.data.body.LoginCredentials
import com.example.smarttrade.auth.data.remote.UserApi
import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.model.Seller
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.network.Exception.NetworkException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun registerClient(user: Client): String {
        try {
            return userApi.registerClient(user)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun registerSeller(user: Seller): String {
        try {
            return userApi.registerSeller(user)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun loginUser(email: String, password: String): String {
        try {
            val credentials = LoginCredentials(email, password)
            return userApi.loginUser(credentials)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}