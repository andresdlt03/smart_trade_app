package com.example.smarttrade.catalogue.domain.repository;

import retrofit2.Response;

interface CatalogueRepository {
    suspend fun getVerifiedProducts(): Response<String>

    suspend fun getUnverifiedProducts(): Response<String>

    suspend fun getProductAvailabilities(productName:String): Response<String>

    suspend fun getProductsSeller(EmailSeller: String): Response<String>

}