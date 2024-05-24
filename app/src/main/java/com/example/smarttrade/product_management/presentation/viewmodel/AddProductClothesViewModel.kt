package com.example.smarttrade.product_management.presentation.viewmodel


import android.net.Uri
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.presentation.viewmodel.state.ProductClothesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddProductClothesViewModel @Inject constructor() : AddProductViewModel() {

    private val _state = MutableStateFlow(ProductClothesState())
    val state = _state.asStateFlow()

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = item)
            2 ->    _state.value = _state.value.copy(description = item)
            3 ->    _state.value = _state.value.copy(size = item)
            4 ->    _state.value = _state.value.copy(price = item)
            5 ->    _state.value = _state.value.copy(stock = item)
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _state.value = _state.value.copy(name = "")
            2 ->    _state.value = _state.value.copy(description = "")
            3 ->    _state.value = _state.value.copy(size = "")
            4 ->    _state.value = _state.value.copy(price = "")
            5 ->    _state.value = _state.value.copy(stock = "")
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

    }

    fun error(): Boolean{
        return (_state.value.textError != "")
    }




}