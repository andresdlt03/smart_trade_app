package com.example.smarttrade.catalogue.data.remote;

import com.example.smarttrade.catalogue.data.remote.http.lists.DeleteFromWishListRequest
import retrofit2.Response;
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers;
import retrofit2.http.PUT
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

    @Headers("content-type: application/json")
    @PUT("products/{productId}")
    suspend fun verifyProduct(@Path("productId") sellerEmail: String): Response<String>

    @Headers("content-type: application/json")
    @GET("api/clients/{clientId}/lists/{listType}")
    suspend fun getList(@Path("listType") listType: String) : Response<String>

    @Headers("content-type: application/json")
    @DELETE("api/clients/{clientId}/lists/wishlist/product")
    suspend fun deleteFromWishList(@Body user: String, @Path("clientId") clientId: String) : Response<String>

}