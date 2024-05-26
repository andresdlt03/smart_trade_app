package com.example.smarttrade.product_management.data.repository

import com.example.smarttrade.network.Exception.NetworkException
import com.example.smarttrade.product_management.data.remote.ProductApi
import com.example.smarttrade.product_management.data.remote.http.CreateProductDTO
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.domain.model.Product
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi
): ProductRepository {
    override suspend fun createProduct(
        product: Product,
        price: Double,
        stock: Int,
        sellerEmail: String,
    ): Response<String> {
        try {
            return productApi.createProduct(
                CreateProductDTO(
                    product = product,
                    price = price,
                    stock = stock,
                    sellerEmail = sellerEmail
                )
            )
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}