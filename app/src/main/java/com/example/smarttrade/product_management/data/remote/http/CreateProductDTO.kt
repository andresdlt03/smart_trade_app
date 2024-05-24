package com.example.smarttrade.product_management.data.remote.http

import com.example.smarttrade.product_management.model.Product

data class CreateProductDTO(
    val product: Product,
    val price: Double,
    val stock: Int,
    val sellerEmail: String,
)
