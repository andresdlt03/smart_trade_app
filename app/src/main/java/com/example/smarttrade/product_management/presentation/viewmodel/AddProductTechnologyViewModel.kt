package com.example.smarttrade.product_management.presentation.viewmodel


import androidx.lifecycle.viewModelScope
import com.example.smarttrade.product_management.domain.model.Technology
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.presentation.validation.ValidateDataSheet
import com.example.smarttrade.product_management.presentation.validation.ValidateDescription
import com.example.smarttrade.product_management.presentation.validation.ValidateExtraFields
import com.example.smarttrade.product_management.presentation.validation.ValidateName
import com.example.smarttrade.product_management.presentation.validation.ValidatePrice
import com.example.smarttrade.product_management.presentation.validation.ValidateStock
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductTechnologyState
import com.example.smarttrade.singleton.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductTechnologyViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val validateName: ValidateName,
    private val validateDescription: ValidateDescription,
    private val validateDataSheet: ValidateDataSheet,
    private val validateExtraFields: ValidateExtraFields,
    private val validatePrice: ValidatePrice,
    private val validateStock: ValidateStock
) : AddProductViewModel(){

    private val _state = MutableStateFlow(ProductTechnologyState())
    val localState = _state.asStateFlow()

    fun updateModel(model: String){
        _state.value = _state.value.copy(model = model)
    }

    fun updateEnergy(energy: String){
        _state.value = _state.value.copy(energy = energy)
    }

    override fun publishProduct() {
        val nameValidation = validateName.execute(super.state.value.name)
        val descriptionValidation = validateDescription.execute(super.state.value.description)
        val dataSheetValidation = validateDataSheet.execute(super.state.value.dataSheet)
        val priceValidation = validatePrice.execute(super.state.value.price)
        val stockValidation = validateStock.execute(super.state.value.stock)
        val modelValidation = validateExtraFields.execute(localState.value.model)
        val energyValidation = validateExtraFields.execute(localState.value.energy)

        val hasError = listOf(
            nameValidation,
            descriptionValidation,
            dataSheetValidation,
            modelValidation,
            energyValidation,
            priceValidation,
            stockValidation
        ).any { !it.successful }

        if (hasError) {
            _state.value = _state.value.copy(
                modelError = modelValidation.errorMessage,
                energyError = energyValidation.errorMessage,
            )
            return
        }

        val product = Technology(
            super.state.value.name,
            super.state.value.description,
            super.state.value.dataSheet,
            listOf(super.state.value.photo1.toString(), super.state.value.photo2.toString()),
            localState.value.energy,
            localState.value.model
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