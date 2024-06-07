package com.example.smarttrade.product_management.data.repository

import com.example.smarttrade.network.Exception.NetworkException
import com.example.smarttrade.product_management.data.remote.ProductApi
import com.example.smarttrade.product_management.data.remote.http.NewProductBody
import com.example.smarttrade.product_management.domain.model.Product
import com.example.smarttrade.product_management.domain.model.ProductAvailability
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
             val body = gson.toJson(NewProductBody(
                 info = product,
                 price = price,
                 stock = stock,
                 sellerEmail = sellerEmail
             ))
            return productApi.createProduct(body, product.category)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun getAvailabilities(
        productId: String
    ): List<ProductAvailability>? {
        try {
            val call = productApi.getAvailabilities(productId)
            if(call.isSuccessful) {
                val responseBody = call.body()
                val listType = object : TypeToken<List<ProductAvailability>>() {}.type
                return gson.fromJson(responseBody.toString(), listType)
            }
            return null
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun deleteProductAvailability(
        productId: String,
        sellerEmail: String
    ): Response<String> {
        try {
            return productApi.deleteProductAvailability(productId, sellerEmail)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}