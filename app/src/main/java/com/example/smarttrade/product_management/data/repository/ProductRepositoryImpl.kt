package com.example.smarttrade.product_management.data.repository

import com.example.smarttrade.network.Exception.NetworkException
import com.example.smarttrade.product_management.data.remote.ProductApi
import com.example.smarttrade.product_management.data.remote.http.CreateProductDTO
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.model.Product
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val gson: Gson
): ProductRepository {
    override suspend fun createProduct(
        product: Product,
        price: Double,
        stock: Int,
        sellerEmail: String,
    ): Response<String> {
        try {
            val productDTO = CreateProductDTO(
                info = product,
                price = price,
                stock = stock,
                sellerEmail = sellerEmail
            )
            val json = gson.toJson(productDTO)

            return productApi.createProduct(product.category, json)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}