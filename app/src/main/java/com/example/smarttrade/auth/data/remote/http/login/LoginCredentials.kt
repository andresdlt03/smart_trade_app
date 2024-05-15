package com.example.smarttrade.auth.data.remote.http.login

import com.google.gson.annotations.SerializedName

data class LoginCredentials (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)