package com.example.smarttrade.product_management.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.product_management.presentation.utils.convertImageToBytes
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
open class AddProductViewModel @Inject constructor(
    private val context: Context
): ViewModel() {

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
        navController.navigate(NavRoutes.ADD_PRODUCT.route)
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

    fun updatePhotos(photo: Uri?){
        if(photo != null){
            _state.value = _state.value.copy(photo = photo)
        }
    }

    fun processPhoto(): ByteArray {
        if(state.value.photo != null){
            return convertImageToBytes(state.value.photo!!, context)!!
        } else {
            return ByteArray(0)
        }
    }

    open fun publishProduct() {}
}