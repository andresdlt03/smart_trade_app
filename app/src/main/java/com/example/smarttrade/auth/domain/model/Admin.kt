package com.example.smarttrade.auth.domain.model

data class Admin (
    override val name: String,
    override val surname: String,
    override val email: String,
    override val password: String
): User