package com.example.smarttrade.auth.domain.model

data class Client(
    override val name: String,
    override val surname: String,
    override val email: String,
    override val password: String,
    val dni: String,
    val deliveryAddress: String,
    val billingAddress: String,
    val creditCard: String
): User