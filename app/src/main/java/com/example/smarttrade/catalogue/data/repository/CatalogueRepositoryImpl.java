package com.example.smarttrade.catalogue.data.repository;

import javax.inject.Inject;
import com.example.smarttrade.catalogue.data.remote.CatalogueApi;


class CatalogueRepositoryImpl @Inject constructor(
        private val CatalogueApi:CatalogueApi,
): CatalogueRepository {

        override suspend fun getVerifiedProducts(): Response<String> {
            try {
            val json = gson.toJson(user)
            return catalogueApi.getVerifiedProducts()
            } catch (e: Exception) {
            throw NetworkException(e.message.toString())
            }
            }
}
