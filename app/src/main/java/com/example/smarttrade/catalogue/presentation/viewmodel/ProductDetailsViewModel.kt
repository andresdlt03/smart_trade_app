package com.example.smarttrade.catalogue.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smarttrade.catalogue.presentation.viewmodel.state.ProductDetailsState
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
open class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel(){

    private val _state = MutableStateFlow(ProductDetailsState())
    val state = _state

    fun updateStockSelected(stockSelected: Int) {
        _state.value = _state.value.copy(stockSelected = stockSelected)
    }
}