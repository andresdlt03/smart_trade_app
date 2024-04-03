package com.example.smarttrade.auth.domain.model

data class User(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = ""
)