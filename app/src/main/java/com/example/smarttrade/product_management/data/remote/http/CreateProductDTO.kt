package com.example.smarttrade.product_management.data.remote.http

data class CreateProductDTO(
    val product: com.example.smarttrade.product_management.model.Product,
    val price: Double,
    val stock: Int,
    val sellerEmail: String,
)
