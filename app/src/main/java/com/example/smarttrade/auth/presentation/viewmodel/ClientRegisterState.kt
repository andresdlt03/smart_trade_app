package com.example.smarttrade.auth.presentation.viewmodel

import com.example.smarttrade.auth.domain.model.User

data class ClientRegisterState (
    val users: List<User> = emptyList(),
)