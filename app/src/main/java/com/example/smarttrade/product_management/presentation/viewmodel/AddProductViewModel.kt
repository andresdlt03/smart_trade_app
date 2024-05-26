package com.example.smarttrade.product_management.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
open class AddProductViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(ProductState())
    val state = _state.asStateFlow()

    fun updateName(name: String){
        _state.value = _state.value.copy(name = name)
    }

    fun updateDescription(description: String){
        _state.value = _state.value.copy(description = description)
    }

    fun updateDataSheet(dataSheet: String){
        _state.value = _state.value.copy(dataSheet = dataSheet)
    }

    fun updatePrice(price: String){
        _state.value = _state.value.copy(price = price)
    }

    fun updateStock(stock: String){
        _state.value = _state.value.copy(stock = stock)
    }

    fun setSuccess(success: Boolean){
        _state.value = _state.value.copy(uploadSuccess = success)
    }

    fun setUploadError(error: String) {
        _state.value = _state.value.copy(uploadError = error)
    }

    fun goBackToCategories(navController: NavHostController){
        navController.navigate("product_management")
    }

    fun setErrors(
        nameError: String?,
        descriptionError: String?,
        priceError: String?,
        stockError: String?,
        dataSheetError: String?
    ) {
        _state.value = _state.value.copy(
            nameError = nameError,
            descriptionError = descriptionError,
            priceError = priceError,
            stockError = stockError,
            dataSheetError = dataSheetError
        )
    }

    fun updatePhotos(p1: Uri?, p2: Uri?){
        if(p1 != null){
            _state.value = _state.value.copy(photo1 = p1)
        }
        if(p2 != null){
            _state.value = _state.value.copy(photo2 = p2)
        }
    }

    open fun publishProduct() {}
}