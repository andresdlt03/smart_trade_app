package com.example.smarttrade.product_management.domain.model

data class ProductAvailability (
    val stock: Int,
    val price: Double,
    val sellerEmail: String
)