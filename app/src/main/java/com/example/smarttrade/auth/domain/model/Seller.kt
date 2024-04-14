package com.example.smarttrade.auth.domain.model

data class Seller (
    override val name: String,
    override val surname: String,
    override val email: String,
    override val password: String,
    val companyName: String,
    val cif: String,
): User