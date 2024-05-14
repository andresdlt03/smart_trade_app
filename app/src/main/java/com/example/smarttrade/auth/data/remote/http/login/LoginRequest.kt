package com.example.smarttrade.auth.data.remote.http.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("credentials")
    val credentials: LoginCredentials
)