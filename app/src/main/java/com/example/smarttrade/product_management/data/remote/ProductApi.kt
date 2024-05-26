package com.example.smarttrade.product_management.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductApi {
    @Headers("content-type: application/json")
    @POST("products/{productCategory}")
    suspend fun createProduct(@Body body: String, @Path("productCategory") category: String): Response<String>
}