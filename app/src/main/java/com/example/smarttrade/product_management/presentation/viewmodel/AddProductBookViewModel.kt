package com.example.smarttrade.product_management.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.model.Book
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
    val state = _state.asStateFlow()

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = item)
            2 ->    _state.value = _state.value.copy(description = item)
            3 ->    _state.value = _state.value.copy(dataSheet = item)
            4 ->    _state.value = _state.value.copy(isbn = item)
            5 ->    _state.value = _state.value.copy(price = item)
            6 ->    _state.value = _state.value.copy(stock = item)
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = "")
            2 ->    _state.value = _state.value.copy(description = "")
            3 ->    _state.value = _state.value.copy(dataSheet = "")
            4 ->    _state.value = _state.value.copy(isbn = "")
            5 ->    _state.value = _state.value.copy(price = "")
            6 ->    _state.value = _state.value.copy(stock = "")
        }
    }

    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("product_management")
    }

    fun updatePhotos(p1: Uri?,p2: Uri?){
        if(p1 != null){_state.value = _state.value.copy(photo1 = p1)}
        if(p2 != null){_state.value = _state.value.copy(photo2 = p2)}
    }

    override fun publishProduct() {
        val nameValidation = validateName.execute(_state.value.name)
        val descriptionValidation = validateDescription.execute(_state.value.description)
        val dataSheetValidation = validateDataSheet.execute(_state.value.dataSheet)
        val isbnValidation = validateExtraFields.execute(_state.value.isbn)
        val priceValidation = validatePrice.execute(_state.value.price)
        val stockValidation = validateStock.execute(_state.value.stock)

        val hasError = listOf(
            nameValidation,
            descriptionValidation,
            dataSheetValidation,
            isbnValidation,
            priceValidation,
            stockValidation
        ).any { !it.successful }

        if (hasError) {
            _state.value = _state.value.copy(
                nameError = nameValidation.errorMessage,
                descriptionError = descriptionValidation.errorMessage,
                dataSheetError = dataSheetValidation.errorMessage,
                isbnError = isbnValidation.errorMessage,
                priceError = priceValidation.errorMessage,
                stockError = stockValidation.errorMessage
            )
            return
        }

        val product = Book(
            _state.value.name,
            _state.value.description,
            _state.value.dataSheet,
            listOf(_state.value.photo1.toString(), _state.value.photo2.toString()),
            "Book",
            _state.value.isbn
        )
        viewModelScope.launch {
            productRepository.createProduct(
                product,
                _state.value.price.toDouble(),
                _state.value.stock.toInt(),
                UserLogged.email
            )
        }
    }

}