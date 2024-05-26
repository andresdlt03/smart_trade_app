package com.example.smarttrade.product_management.presentation.viewmodel


import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.product_management.domain.model.Clothes
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.presentation.validation.ValidateDataSheet
import com.example.smarttrade.product_management.presentation.validation.ValidateDescription
import com.example.smarttrade.product_management.presentation.validation.ValidateExtraFields
import com.example.smarttrade.product_management.presentation.validation.ValidateName
import com.example.smarttrade.product_management.presentation.validation.ValidatePrice
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductClothesState
import com.example.smarttrade.singleton.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductClothesViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val validateName: ValidateName,
    private val validateDescription: ValidateDescription,
    private val validateDataSheet: ValidateDataSheet,
    private val validateExtraFields: ValidateExtraFields,
    private val validatePrice: ValidatePrice,
    private val validateStock: ValidatePrice,
    private val context: Context
) : AddProductViewModel(context) {

    private val _state = MutableStateFlow(ProductClothesState())
    val localState = _state.asStateFlow()

    fun updateSize(size: String) {
        _state.value = _state.value.copy(size = size)
    }

    override fun publishProduct() {
        val nameValidation = validateName.execute(super.state.value.name)
        val descriptionValidation = validateDescription.execute(super.state.value.description)
        val dataSheetValidation = validateDataSheet.execute(super.state.value.dataSheet)
        val priceValidation = validatePrice.execute(super.state.value.price)
        val stockValidation = validateStock.execute(super.state.value.stock)
        val sizeValidation = validateExtraFields.execute(_state.value.size)

        val hasError = listOf(
            nameValidation,
            descriptionValidation,
            dataSheetValidation,
            sizeValidation,
            priceValidation,
            stockValidation
        ).any { !it.successful }

        if (hasError) {
            super.setErrors(
                nameValidation.errorMessage,
                descriptionValidation.errorMessage,
                dataSheetValidation.errorMessage,
                priceValidation.errorMessage,
                stockValidation.errorMessage
            )
            _state.value = _state.value.copy(
                sizeError = sizeValidation.errorMessage,
            )
            return
        }

        val product = Clothes(
            super.state.value.name,
            super.state.value.description,
            super.state.value.dataSheet,
            processPhoto(),
            localState.value.size
        )
        viewModelScope.launch {
            uploadNewProduct(
                product,
                super.state.value.price.toDouble(),
                super.state.value.stock.toInt(),
                UserLogged.email,
                productRepository,
                {result -> setSuccess(result)},
                {error -> setUploadError(error)}
            )
        }
    }

}