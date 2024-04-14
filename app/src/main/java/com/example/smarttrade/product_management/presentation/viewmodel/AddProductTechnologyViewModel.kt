package com.example.smarttrade.product_management.presentation.viewmodel


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductTechnologyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AddProductTechnologyViewModel : ViewModel(){

    private val _state = MutableStateFlow(ProductTechnologyState())
    val state = _state.asStateFlow()

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _state.value.name = item
            2 ->    _state.value.description = item
            3 ->    _state.value.model = item
            4 ->    _state.value.energy = item
            5 ->    _state.value.price = item
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _state.value.name = ""
            2 ->    _state.value.description = ""
            3 ->    _state.value.model = ""
            4 ->    _state.value.energy = ""
            5 ->    _state.value.price = ""
        }
    }

    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("main")
    }

    fun updatePhotos(p1: Uri?, p2: Uri?){
        if(p1 != null){_state.value.photo1 = p1}
        if(p2 != null){_state.value.photo2 = p2}
    }

    fun checkAllVariables() {
        _state.value.checkVariables = true
        _state.value.textError = "Todo correcto"

        if (_state.value.name.isNullOrEmpty()) {
            _state.value.checkVariables = false
            _state.value.textError = "El nombre está vacío"
            return
        } else if (_state.value.name.length > 20) {
            _state.value.checkVariables = false
            _state.value.textError = "El nombre es demasiado largo"
            return
        } else if (_state.value.description.isNullOrEmpty()) {
            _state.value.checkVariables = false
            _state.value.textError = "La descripción está vacía"
            return
        } else if (_state.value.description.length > 50) {
            _state.value.checkVariables = false
            _state.value.textError = "La descripción es demasiado larga"
            return
        } else if (_state.value.model.isNullOrEmpty()) {
            _state.value.checkVariables = false
            _state.value.textError = "El modelo está vacío"
            return
        } else if (_state.value.model.length > 20) {
            _state.value.checkVariables = false
            _state.value.textError = "El modelo es demasiado largo"
            return
        } else if (_state.value.energy.isNullOrEmpty()) {
            _state.value.checkVariables = false
            _state.value.textError = "El consumo energético está vacío"
            return
        } else {
            val energyFloat = _state.value.energy.toFloatOrNull()
            if (energyFloat == null) {
                _state.value.checkVariables = false
                _state.value.textError = "El consumo energético debe ser un número"
                return
            }
        }

        if (_state.value.price.isNullOrEmpty()) {
            _state.value.checkVariables = false
            _state.value.textError = "El precio está vacío"
            return
        } else {
            val priceFloat = _state.value.price.toFloatOrNull()
            if (priceFloat == null) {
                _state.value.checkVariables = false
                _state.value.textError = "El precio debe ser un número"
                return
            }
        }

        if (_state.value.photo1 == null) {
            _state.value.checkVariables = false
            _state.value.textError = "Debes seleccionar al menos 1 foto"
            return
        }
    }


}