package com.example.smarttrade.product_management.data.remote

import com.example.smarttrade.product_management.data.remote.http.NewProduct
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface productApi {
    @Headers("content-type: application/json")
    @POST("products/{productCategory}")
    suspend fun createProduct(@Body newProduct: NewProduct, @Path("userType") userType: String): Response<String>
}