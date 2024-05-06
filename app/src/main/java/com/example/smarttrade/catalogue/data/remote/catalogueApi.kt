package com.example.smarttrade.catalogue.data.remote;

import retrofit2.Response;
import retrofit2.http.GET
import retrofit2.http.Headers;
import retrofit2.http.Path

interface CatalogueApi {
    @Headers("content-type: application/json")
    @GET("products/verified")
    suspend fun getVerifiedProducts(): Response<String>

    @Headers("content-type: application/json")
    @GET("products/unverified")
    suspend fun getUnverifiedProducts(): Response<String>

    @Headers("content-type: application/json")
    @GET("products//{productId}/availabilities")
    suspend fun getProductAvailabilities(@Path("productId") productId: String): Response<String>

    @Headers("content-type: application/json")
    @GET("products/seller/{sellerEmail}")
    suspend fun getProductsSeller(@Path("sellerEmail") sellerEmail: String): Response<String>
}