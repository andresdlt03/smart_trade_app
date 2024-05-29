package com.example.smarttrade.catalogue.domain.repository;

import com.example.smarttrade.catalogue.data.remote.http.lists.CarritoListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.GuardarTardeListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.WishListRequest
import retrofit2.Response;

interface CatalogueRepository {
    suspend fun getVerifiedProducts(): Response<String>

    suspend fun getUnverifiedProducts(): Response<String>

    suspend fun getProductAvailabilities(productName:String): Response<String>

    suspend fun getProductsSeller(emailSeller: String): Response<String>

    suspend fun verifyProduct(productId: String): Response<String>

    suspend fun getList(listType: String): Response<String>

    suspend fun deleteFromWishList(deleteWishList: WishListRequest, clientId: String): Response<String>

    suspend fun deleteFromCarritoList(deleteCarritoList: CarritoListRequest, clientId: String): Response<String>

    suspend fun deleteFromGuardarTardeList(deleteGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String>

    suspend fun addToWishList(addWishList: WishListRequest, clientId: String): Response<String>

    suspend fun addToCarritoList(addCarritoList: CarritoListRequest, clientId: String): Response<String>

    suspend fun addToGuardarTardeList(addGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String>

}