package com.example.smarttrade.product_management.data.remote

import com.example.smarttrade.product_management.data.remote.http.CreateProductDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProductApi {
    @Headers("content-type: application/json")
    @POST("products/{productCategory}")
    suspend fun createProduct(@Body productDTO: CreateProductDTO): Response<String>
}