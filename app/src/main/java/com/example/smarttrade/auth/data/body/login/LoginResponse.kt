package com.example.smarttrade.auth.data.body.login

data class LoginResponse (
    val loginCredentials: LoginCredentials? = null,
    val errorMessage: String? = null
    )