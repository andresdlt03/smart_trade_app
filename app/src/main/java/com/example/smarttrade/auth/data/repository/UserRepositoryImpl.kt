package com.example.smarttrade.auth.data.repository

import com.example.smarttrade.auth.data.remote.UserApi
import com.example.smarttrade.auth.domain.model.Client
import com.example.smarttrade.auth.domain.model.Seller
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.auth.http.login.LoginCredentials
import com.example.smarttrade.auth.http.login.LoginRequest
import com.example.smarttrade.network.Exception.NetworkException
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun registerClient(user: Client): Response<String> {
        try {
            val gson: Gson = Gson()
            val json = gson.toJson(user)
            return userApi.registerClient(json)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun registerSeller(user: Seller): Response<String> {
        try {
            return userApi.registerSeller(user)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun loginUser(email: String, password: String): Response<String> {
        try {
            val credentials = LoginCredentials(email, password)
            val loginRequest = LoginRequest(credentials)
            return userApi.loginUser(loginRequest)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}