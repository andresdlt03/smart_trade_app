package com.example.smarttrade.auth.data.remote

import com.example.smarttrade.auth.domain.model.User
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers() : List<User>
}