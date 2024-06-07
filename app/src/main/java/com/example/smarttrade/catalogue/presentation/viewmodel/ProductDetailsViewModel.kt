package com.example.smarttrade.catalogue.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.catalogue.presentation.viewmodel.state.ProductDetailsState
import com.example.smarttrade.lists.CartState
import com.example.smarttrade.lists.viewmodel.state.CartItem
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.singleton.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel(){

    private val _state = MutableStateFlow(ProductDetailsState())
    val state = _state

    fun initializeAvailabilities(productId: String) {
        refreshAvailabilities(productId)
    }

    fun updateStockSelected(stockSelected: String) {
        _state.value = _state.value.copy(stockSelected = stockSelected)
    }

    fun updatePriceSelected(priceSelected: Double) {
        _state.value = _state.value.copy(priceSelected = priceSelected)
    }

    fun deleteProductAvailability(productId: String) {
        viewModelScope.launch {
            productRepository.deleteProductAvailability(productId, UserLogged.email)
        }
    }

    fun addProductToCart(productId: String, price: Double, quantity: Int) {
        CartState.addToCart(
            CartItem(
                productName = productId,
                price = price,
                quantity = quantity
            ),
        )
    }

    fun refreshAvailabilities(productId: String){
        viewModelScope.launch {
            val availabilities = productRepository.getAvailabilities(productId)
            _state.value = _state.value.copy(availabilities = availabilities ?: emptyList())
            val availabilitiesDraft = mutableListOf<String>()
            for(availability in availabilities!!){
                availabilitiesDraft.add("${availability.sellerEmail} - ${availability.price} € (${availability.stock} en stock)")
            }
            _state.value = _state.value.copy(availabilitiesString = availabilitiesDraft)
        }
    }
}