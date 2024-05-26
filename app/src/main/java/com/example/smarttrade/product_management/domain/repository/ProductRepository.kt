package com.example.smarttrade.product_management.domain.repository

import com.example.smarttrade.product_management.domain.model.Product
import retrofit2.Response

interface ProductRepository {
    suspend fun createProduct(
        product: Product,
        price: Double,
        stock: Int,
        sellerEmail: String,
    ): Response<String>
}