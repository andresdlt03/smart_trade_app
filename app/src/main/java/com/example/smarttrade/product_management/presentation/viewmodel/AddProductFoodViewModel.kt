package com.example.smarttrade.product_management.presentation.viewmodel


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductFoodState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddProductFoodViewModel @Inject constructor() : ViewModel(){

    private val _state = MutableStateFlow(ProductFoodState())
    val state = _state.asStateFlow()

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = item)
            2 ->    _state.value = _state.value.copy(description = item)
            3 ->    _state.value = _state.value.copy(calories = item)
            4 ->    _state.value = _state.value.copy(price = item)
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = "")
            2 ->    _state.value = _state.value.copy(description = "")
            3 ->    _state.value = _state.value.copy(calories = "")
            4 ->    _state.value = _state.value.copy(price = "")
        }
    }

    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("product_management")
    }

    fun updatePhotos(p1: Uri?, p2: Uri?){
        if(p1 != null){_state.value = _state.value.copy(photo1 = p1)}
        if(p2 != null){_state.value = _state.value.copy(photo2 = p2)}
    }

    fun checkAllVariables() {
        _state.value = _state.value.copy(checkVariables = true)
        _state.value = _state.value.copy(textError = "")

        if (_state.value.name.isNullOrEmpty()) {
            _state.value = _state.value.copy(checkVariables = false)
            _state.value = _state.value.copy(textError = "El nombre está vacío")
            return
        } else if (_state.value.name.length > 20) {
            _state.value = _state.value.copy(checkVariables = false)
            _state.value = _state.value.copy(textError = "El nombre es demasiado largo")
            return
        } else if (_state.value.description.isNullOrEmpty()) {
            _state.value = _state.value.copy(checkVariables = false)
            _state.value = _state.value.copy(textError = "La descripción está vacía")
            return
        } else if (_state.value.description.length > 50) {
            _state.value = _state.value.copy(checkVariables = false)
            _state.value = _state.value.copy(textError = "La descripción es demasiado larga")
            return
        } else if (_state.value.calories.isNullOrEmpty()) {
            _state.value = _state.value.copy(checkVariables = false)
            _state.value = _state.value.copy(textError = "Las calorías están vacías")
            return
        } else {
            val caloriasInt = _state.value.calories.toIntOrNull()
            if (caloriasInt == null || caloriasInt <= 0) {
                _state.value = _state.value.copy(checkVariables = false)
                _state.value = _state.value.copy(textError = "Las calorías no son válidas")
                return
            }
        }

        if (_state.value.photo1 == null) {
            _state.value = _state.value.copy(checkVariables = false)
            _state.value = _state.value.copy(textError = "La foto 1 está vacía")
            return
        }
    }


}