package com.example.smarttrade.catalogue.data.remote.http.lists;

import com.google.gson.annotations.SerializedName;

data class GuardarTardeListRequest (
    @SerializedName("sellerEmail")
    val sellerEmail: String,
    @SerializedName("productId")
    val productId: String
)
