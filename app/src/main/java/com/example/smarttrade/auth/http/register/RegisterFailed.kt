package com.example.smarttrade.auth.http.register

data class RegisterFailed (
    val errorMessage: String? = null,
    val email: String? = null
)