package com.example.smarttrade.catalogue.presentation.viewmodel.state

import com.example.smarttrade.catalogue.data.repository.ProductWrapper

data class HomeCatalogueState (
    val products: List<ProductWrapper>? = null,
    val search: String = "",

)