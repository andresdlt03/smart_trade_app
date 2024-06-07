package com.example.smarttrade.product_management.domain.repository

import com.example.smarttrade.product_management.domain.model.Product
import com.example.smarttrade.product_management.domain.model.ProductAvailability
import retrofit2.Response

interface ProductRepository {
    suspend fun createProduct(
        product: Product,
        price: Double,
        stock: Int,
        sellerEmail: String,
    ): Response<String>

    suspend fun getAvailabilities(
        productId: String
    ): List<ProductAvailability>?

    suspend fun deleteProductAvailability(
        productId: String,
        sellerEmail: String
    ): Response<String>
}