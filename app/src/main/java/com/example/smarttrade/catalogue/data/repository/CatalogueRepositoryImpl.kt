package com.example.smarttrade.catalogue.data.repository;

import com.example.smarttrade.catalogue.data.remote.CatalogueApi
import com.example.smarttrade.catalogue.data.remote.http.lists.CarritoListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.GuardarTardeListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.WishListRequest
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import com.example.smarttrade.network.Exception.NetworkException
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class CatalogueRepositoryImpl @Inject constructor(
    private val catalogueApi: CatalogueApi,
    private val gson: Gson
): CatalogueRepository {

    override suspend fun getVerifiedProducts(): List<ProductWrapper>? {
        try {
            val call = catalogueApi.getVerifiedProducts()
            if(call.isSuccessful) {
                val responseBody = call.body()
                val productJsonWrappers = parseProductJsonWrappers(responseBody.toString())
                return convertToProductWrappers(productJsonWrappers)
            }
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
        return null;
    }

    override suspend fun getUnverifiedProducts(): List<ProductWrapper>? {
        try {
            val call = catalogueApi.getUnverifiedProducts()
            if(call.isSuccessful) {
                val responseBody = call.body()
                val productJsonWrappers = parseProductJsonWrappers(responseBody.toString())
                return convertToProductWrappers(productJsonWrappers)
            }
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
        return null;
    }

    override suspend fun getProductAvailabilities(productName:String): Response<String> {
        try {
            return catalogueApi.getProductAvailabilities(productName)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun getProductsSeller(email: String): List<ProductWrapper>? {
        try {
            val call = catalogueApi.getProductsSeller(email)
            if(call.isSuccessful) {
                val responseBody = call.body()
                val productJsonWrappers = parseProductJsonWrappers(responseBody.toString())
                return convertToProductWrappers(productJsonWrappers)
            }
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
        return null;
    }

    override suspend fun verifyProduct(productId: String): Response<String> {
        try {
            return catalogueApi.verifyProduct(productId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun getList(listType: String): Response<String> {
        try {
            return catalogueApi.getList(listType)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun deleteFromWishList(deleteWishList: WishListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(deleteWishList)
            return catalogueApi.deleteFromWishList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun deleteFromCarritoList(deleteCarritoList: CarritoListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(deleteCarritoList)
            return catalogueApi.deleteFromCarritoList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun deleteFromGuardarTardeList(deleteGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(deleteGuardarTardeList)
            return catalogueApi.deleteFromGuardarTardeList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun addToWishList(addWishList: WishListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(addWishList)
            return catalogueApi.addToWishList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun addToCarritoList(addCarritoList: CarritoListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(addCarritoList)
            return catalogueApi.addToCarritoList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun addToGuardarTardeList(addGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(addGuardarTardeList)
            return catalogueApi.addToGuardarTardeList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}