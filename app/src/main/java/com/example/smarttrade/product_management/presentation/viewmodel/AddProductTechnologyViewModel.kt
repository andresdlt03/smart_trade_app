package com.example.smarttrade.product_management.presentation.viewmodel


import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.domain.repository.ProductRepository
import com.example.smarttrade.product_management.model.Technology
import com.example.smarttrade.product_management.presentation.validation.ValidateDataSheet
import com.example.smarttrade.product_management.presentation.validation.ValidateDescription
import com.example.smarttrade.product_management.presentation.validation.ValidateExtraFields
import com.example.smarttrade.product_management.presentation.validation.ValidateName
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductTechnologyState
import com.example.smarttrade.singleton.UserLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductTechnologyViewModel @Inject constructor(
    val productRepository: ProductRepository,
    val validateName: ValidateName,
    val validateDescription: ValidateDescription,
    val validateDataSheet: ValidateDataSheet,
    val validateExtraFields: ValidateExtraFields,
) : AddProductViewModel(){

    private val _state = MutableStateFlow(ProductTechnologyState())
    val state = _state.asStateFlow()

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = item)
            2 ->    _state.value = _state.value.copy(description = item)
            3 ->    _state.value = _state.value.copy(model = item)
            4 ->    _state.value = _state.value.copy(energy = item)
            5 ->    _state.value = _state.value.copy(price = item)
            6 ->    _state.value = _state.value.copy(stock = item)
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = "")
            2 ->    _state.value = _state.value.copy(description = "")
            3 ->    _state.value = _state.value.copy(model = "")
            4 ->    _state.value = _state.value.copy(energy = "")
            5 ->    _state.value = _state.value.copy(price = "")
            6 ->    _state.value = _state.value.copy(stock = "")
        }
    }

    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("product_management")
    }

    fun updatePhotos(p1: Uri?, p2: Uri?){
        if(p1 != null){_state.value = _state.value.copy(photo1 = p1)}
        if(p2 != null){_state.value = _state.value.copy(photo2 = p2)}
    }

    override fun publishProduct() {
        val nameValidation = validateName.execute(_state.value.name)
        val descriptionValidation = validateDescription.execute(_state.value.description)
        val dataSheetValidation = validateDataSheet.execute(_state.value.dataSheet)
        val modelValidation = validateExtraFields.execute(_state.value.model)
        val energyValidation = validateExtraFields.execute(_state.value.energy)

        val hasError = listOf(
            nameValidation,
            descriptionValidation,
            dataSheetValidation,
            modelValidation,
            energyValidation
        ).any { !it.successful }

        if (hasError) {
            _state.value = _state.value.copy(
                nameError = nameValidation.errorMessage,
                descriptionError = descriptionValidation.errorMessage,
                dataSheetError = dataSheetValidation.errorMessage,
                modelError = modelValidation.errorMessage,
                energyError = energyValidation.errorMessage
            )
            return
        }

        val product = Technology(
            _state.value.name,
            _state.value.description,
            _state.value.dataSheet,
            listOf(_state.value.photo1.toString(), _state.value.photo2.toString()),
            "Technology",
            _state.value.energy,
            _state.value.model
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