package com.example.smarttrade.catalogue.domain.repository;

import com.example.smarttrade.catalogue.data.remote.http.lists.CarritoListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.GuardarTardeListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.WishListRequest
import com.example.smarttrade.catalogue.data.repository.ProductWrapper
import retrofit2.Response;

interface CatalogueRepository {

    suspend fun getProductById(productId: String): ProductWrapper?
    suspend fun getVerifiedProducts(): List<ProductWrapper>?

    suspend fun getUnverifiedProducts(): List<ProductWrapper>?

    suspend fun getProductAvailabilities(productName:String): Response<String>

    suspend fun getProductsSeller(emailSeller: String): List<ProductWrapper>?

    suspend fun verifyProduct(productId: String, verify: Boolean): Response<String>

    suspend fun getList(listType: String): Response<String>

    suspend fun deleteFromWishList(deleteWishList: WishListRequest, clientId: String): Response<String>

    suspend fun deleteFromCarritoList(deleteCarritoList: CarritoListRequest, clientId: String): Response<String>

    suspend fun deleteFromGuardarTardeList(deleteGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String>

    suspend fun addToWishList(addWishList: WishListRequest, clientId: String): Response<String>

    suspend fun addToCarritoList(addCarritoList: CarritoListRequest, clientId: String): Response<String>

    suspend fun addToGuardarTardeList(addGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String>

}