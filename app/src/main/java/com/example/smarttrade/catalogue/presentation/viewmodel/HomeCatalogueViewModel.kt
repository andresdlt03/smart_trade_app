package com.example.smarttrade.catalogue.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.catalogue.data.repository.ProductWrapper
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import com.example.smarttrade.catalogue.presentation.viewmodel.state.HomeCatalogueState
import com.example.smarttrade.network.Exception.NetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeCatalogueViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository,
): ViewModel(){

    private val _state = MutableStateFlow(HomeCatalogueState())
    val state = _state

    fun initializeProducts() {
        refreshUnverifiedProducts()
    }

    fun updateProducts(products: List<ProductWrapper>){
        _state.value = _state.value.copy(products = products)
    }

    fun updateSearch(search: String) {
        _state.value = _state.value.copy(search = search)
    }

    fun refreshUnverifiedProducts(){
        viewModelScope.launch {
            try {
                val productWrappers = catalogueRepository.getVerifiedProducts();
                if(productWrappers != null) {
                    updateProducts(productWrappers)
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }
    }
}