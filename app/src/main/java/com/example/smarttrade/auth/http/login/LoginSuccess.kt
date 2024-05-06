package com.example.smarttrade.auth.http.login

import com.google.gson.annotations.SerializedName

data class LoginSuccess (
    @SerializedName("email")
    val email: String,
    @SerializedName("userType")
    val userType: String,
)