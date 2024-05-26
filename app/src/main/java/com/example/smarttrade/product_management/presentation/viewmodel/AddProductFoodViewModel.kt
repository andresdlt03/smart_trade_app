package com.example.smarttrade.product_management.presentation.viewmodel


import androidx.lifecycle.viewModelScope
import com.example.smarttrade.product_management.domain.model.Food
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.presentation.validation.ValidateDataSheet
import com.example.smarttrade.product_management.presentation.validation.ValidateDescription
import com.example.smarttrade.product_management.presentation.validation.ValidateExtraFields
import com.example.smarttrade.product_management.presentation.validation.ValidateName
import com.example.smarttrade.product_management.presentation.validation.ValidatePrice
import com.example.smarttrade.product_management.presentation.validation.ValidateStock
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductFoodState
import com.example.smarttrade.singleton.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductFoodViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val validateName: ValidateName,
    private val validateDescription: ValidateDescription,
    private val validateDataSheet: ValidateDataSheet,
    private val validateExtraFields: ValidateExtraFields,
    private val validatePrice: ValidatePrice,
    private val validateStock: ValidateStock

) : AddProductViewModel(){

    private val _state = MutableStateFlow(ProductFoodState())
    val localState = _state.asStateFlow()

    fun updateCalories(calories: String) {
        _state.value = _state.value.copy(calories = calories)
    }

    override fun publishProduct() {
        val nameValidation = validateName.execute(super.state.value.name)
        val descriptionValidation = validateDescription.execute(super.state.value.description)
        val dataSheetValidation = validateDataSheet.execute(super.state.value.dataSheet)
        val priceValidation = validatePrice.execute(super.state.value.price)
        val stockValidation = validateStock.execute(super.state.value.stock);
        val caloriesValidation = validateExtraFields.execute(_state.value.calories)

        val hasError = listOf(
            nameValidation,
            descriptionValidation,
            dataSheetValidation,
            caloriesValidation,
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
                caloriesError = caloriesValidation.errorMessage,
            )
            return
        }

        val product = Food(
            super.state.value.name,
            super.state.value.description,
            super.state.value.dataSheet,
            listOf(super.state.value.photo1.toString(), super.state.value.photo2.toString()),
            localState.value.calories
        )
        viewModelScope.launch {
            productRepository.createProduct(
                product,
                super.state.value.price.toDouble(),
                super.state.value.stock.toInt(),
                UserLogged.email
            )
        }
    }
}