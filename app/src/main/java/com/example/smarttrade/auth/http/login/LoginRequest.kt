package com.example.smarttrade.auth.http.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("credentials")
    val credentials: LoginCredentials
)