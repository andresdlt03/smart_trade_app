package com.example.smarttrade.catalogue.data.remote;

import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CatalogueApi {


    @Headers("content-type: application/json")
    @POST("users/{userType}s")
    suspend fun getVerifiedProducts(): Response<String>
}
