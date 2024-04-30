package com.example.smarttrade.auth.data.repository

import com.example.smarttrade.auth.data.remote.UserApi
import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.auth.domain.repository.UserRepository
import com.example.smarttrade.auth.http.login.LoginCredentials
import com.example.smarttrade.auth.http.login.LoginRequest
import com.example.smarttrade.network.Exception.NetworkException
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val gson: Gson
) : UserRepository {
    override suspend fun registerUser(user: User, userType: String): Response<String> {
        try {
            val json = gson.toJson(user)
            return userApi.registerUser(json, userType)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun loginUser(email: String, password: String): Response<String> {
        try {
            val credentials = LoginCredentials(email, password)
            val loginRequest = LoginRequest(credentials)
            val json = gson.toJson(loginRequest)
            return userApi.loginUser(json)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}