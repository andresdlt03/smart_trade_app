package com.example.smarttrade.auth.presentation.viewmodel

data class ClientRegisterState (
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val dni: String = ""
)