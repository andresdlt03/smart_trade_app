package com.example.smarttrade.catalogue.domain.repository;

import com.example.smarttrade.catalogue.data.remote.http.lists.DeleteFromWishListRequest
import retrofit2.Response;

interface CatalogueRepository {
    suspend fun getVerifiedProducts(): Response<String>

    suspend fun getUnverifiedProducts(): Response<String>

    suspend fun getProductAvailabilities(productName:String): Response<String>

    suspend fun getProductsSeller(EmailSeller: String): Response<String>

    suspend fun verifyProduct(productId: String): Response<String>

    suspend fun getList(listType: String): Response<String>

    suspend fun deleteFromWishList(deleteWishList: DeleteFromWishListRequest, clientId: String): Response<String>

}