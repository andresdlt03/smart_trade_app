package com.example.smarttrade.product_management.domain.repository

import retrofit2.Response

interface productRepository {
    suspend fun createProduct(product: , productCategory: String): Response<String>;
}