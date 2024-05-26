package com.example.smarttrade.product_management.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.smarttrade.product_management.domain.model.Book
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.presentation.validation.ValidateDataSheet
import com.example.smarttrade.product_management.presentation.validation.ValidateDescription
import com.example.smarttrade.product_management.presentation.validation.ValidateExtraFields
import com.example.smarttrade.product_management.presentation.validation.ValidateName
import com.example.smarttrade.product_management.presentation.validation.ValidatePrice
import com.example.smarttrade.product_management.presentation.validation.ValidateStock
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductBookState
import com.example.smarttrade.singleton.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductBookViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val validateName: ValidateName,
    private val validateDescription: ValidateDescription,
    private val validateDataSheet: ValidateDataSheet,
    private val validateExtraFields: ValidateExtraFields,
    private val validatePrice: ValidatePrice,
    private val validateStock: ValidateStock
) : AddProductViewModel() {

    private val _state = MutableStateFlow(ProductBookState())
    val localState = _state.asStateFlow()

    fun updateIsbn(isbn: String) {
        _state.value = _state.value.copy(isbn = isbn)
    }

    override fun publishProduct() {
        val nameValidation = validateName.execute(super.state.value.name)
        val descriptionValidation = validateDescription.execute(super.state.value.description)
        val dataSheetValidation = validateDataSheet.execute(super.state.value.dataSheet)
        val priceValidation = validatePrice.execute(super.state.value.price)
        val stockValidation = validateStock.execute(super.state.value.stock)
        val isbnValidation = validateExtraFields.execute(localState.value.isbn)

        val hasError = listOf(
            nameValidation,
            descriptionValidation,
            dataSheetValidation,
            isbnValidation,
            priceValidation,
            stockValidation
        ).any { !it.successful }

        if (hasError) {
            super.setErrors(
                nameValidation.errorMessage,
                descriptionValidation.errorMessage,
                priceValidation.errorMessage,
                stockValidation.errorMessage,
                dataSheetValidation.errorMessage
            )
            _state.value = _state.value.copy(
                isbnError = isbnValidation.errorMessage,
            )
            return
        }
        viewModelScope.launch {
            val product = Book(
                super.state.value.name,
                super.state.value.description,
                super.state.value.dataSheet,
                listOf(super.state.value.photo1.toString(), super.state.value.photo2.toString()),
                localState.value.isbn
            )
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