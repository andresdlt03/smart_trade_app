package com.example.smarttrade.product_management.presentation.viewmodel


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductClothesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddProductClothesViewModel : ViewModel(){

    private val _state = MutableStateFlow(ProductClothesState())
    val state = _state.asStateFlow()

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _state.value.name = item
            2 ->    _state.value.description = item
            3 ->    _state.value.size = item
            4 ->    _state.value.price = item
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _state.value.name = ""
            2 ->    _state.value.description = ""
            3 ->    _state.value.size = ""
            4 ->    _state.value.price = ""
        }
    }

    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("main")
    }

    fun updatePhotos(p1: Uri?,p2: Uri?){
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
        } else if (_state.value.size.isNullOrEmpty()) {
            _state.value.checkVariables = false
            _state.value.textError = "La talla está vacía"
            return
        }

        if (_state.value.photo1 == null) {
            _state.value.checkVariables = false
            _state.value.textError = "Debes seleccionar al menos 1 foto"
            return
        }
    }

}