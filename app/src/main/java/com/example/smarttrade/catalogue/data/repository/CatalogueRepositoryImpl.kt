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
    private val CatalogueApi: CatalogueApi,
    private val gson: Gson

): CatalogueRepository {

    override suspend fun getVerifiedProducts(): Response<String> {
        try {
            return CatalogueApi.getVerifiedProducts()
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun getUnverifiedProducts(): Response<String> {
        try {
            return CatalogueApi.getUnverifiedProducts()
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun getProductAvailabilities(productName:String): Response<String> {
        try {
            return CatalogueApi.getProductAvailabilities(productName)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun getProductsSeller(EmailSeller: String): Response<String> {
        try {
            return CatalogueApi.getProductsSeller(EmailSeller)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun verifyProduct(productId: String): Response<String> {
        try {
            return CatalogueApi.verifyProduct(productId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun getList(listType: String): Response<String> {
        try {
            return CatalogueApi.getList(listType)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun deleteFromWishList(deleteWishList: WishListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(deleteWishList)
            return CatalogueApi.deleteFromWishList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun deleteFromCarritoList(deleteCarritoList: CarritoListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(deleteCarritoList)
            return CatalogueApi.deleteFromCarritoList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun deleteFromGuardarTardeList(deleteGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(deleteGuardarTardeList)
            return CatalogueApi.deleteFromGuardarTardeList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun addToWishList(addWishList: WishListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(addWishList)
            return CatalogueApi.addToWishList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun addToCarritoList(addCarritoList: CarritoListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(addCarritoList)
            return CatalogueApi.addToCarritoList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }

    override suspend fun addToGuardarTardeList(addGuardarTardeList: GuardarTardeListRequest, clientId: String): Response<String> {
        try {
            val json = gson.toJson(addGuardarTardeList)
            return CatalogueApi.addToGuardarTardeList(json,clientId)
        } catch (e: Exception) {
            throw NetworkException(e.message.toString())
        }
    }
}