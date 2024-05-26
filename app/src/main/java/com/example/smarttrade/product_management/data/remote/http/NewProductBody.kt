package com.example.smarttrade.product_management.data.remote.http

import com.example.smarttrade.product_management.domain.model.Product

data class NewProductBody(
    val info: Product,
    val price: Double,
    val stock: Int,
    val sellerEmail: String,
)
