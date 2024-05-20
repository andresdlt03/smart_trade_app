package com.example.smarttrade.catalogue.data.remote.http.lists;

import com.google.gson.annotations.SerializedName;

data class CarritoListRequest (
    @SerializedName("sellerEmail")
    val sellerEmail: String,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("quantity")
    val quantity: String,
)

