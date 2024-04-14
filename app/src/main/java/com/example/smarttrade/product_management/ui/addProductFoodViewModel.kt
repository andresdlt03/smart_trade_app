package com.example.smarttrade.product_management.ui


import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class `addProductFoodViewModel` : ViewModel(){

    // id = 1
    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    // id = 2
    private val _description = MutableLiveData<String>()
    val description : LiveData<String> = _description

    // id = 3
    private val _calories = MutableLiveData<String>()
    val calories : LiveData<String> = _calories

    // id = 4
    private val _price = MutableLiveData<String>()
    val price : LiveData<String> = _price

    private val _photo1 = MutableLiveData<Uri?>()
    val photo1 : LiveData<Uri?> = _photo1

    private val _photo2 = MutableLiveData<Uri?>()
    val photo2 : LiveData<Uri?> = _photo2

    private val _checkVariables = MutableLiveData<Boolean>()
    val checkVariables : LiveData<Boolean> = _checkVariables

    private val _textError = MutableLiveData<String>()
    val textError : LiveData<String> = _textError

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _name.value = item
            2 ->    _description.value = item
            3 ->    _calories.value = item
            4 ->    _price.value = item
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _name.value = ""
            2 ->    _description.value = ""
            3 ->    _calories.value = ""
            4 ->    _price.value = ""
        }
    }

    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("main")
    }

    fun updatePhotos(p1: Uri?, p2: Uri?){
        if(p1 != null){_photo1.value = p1}
        if(p2 != null){_photo2.value = p2}
    }

    fun checkAllVariables() {
        _checkVariables.value = true
        _textError.value = "Todo correcto"

        if (_name.value.isNullOrEmpty()) {
            _checkVariables.value = false
            _textError.value = "El nombre está vacío"
            return
        } else if (_name.value!!.length > 20) {
            _checkVariables.value = false
            _textError.value = "El nombre es demasiado largo"
            return
        } else if (_description.value.isNullOrEmpty()) {
            _checkVariables.value = false
            _textError.value = "La descripción está vacía"
            return
        } else if (_description.value!!.length > 50) {
            _checkVariables.value = false
            _textError.value = "La descripción es demasiado larga"
            return
        } else if (_calories.value.isNullOrEmpty()) {
            _checkVariables.value = false
            _textError.value = "Las calorías están vacías"
            return
        } else {
            val caloriasInt = _calories.value!!.toIntOrNull()
            if (caloriasInt == null || caloriasInt <= 0) {
                _checkVariables.value = false
                _textError.value = "Las calorías deben ser un número entero positivo"
                return
            }
        }

        if (_photo1.value == null) {
            _checkVariables.value = false
            _textError.value = "Debes seleccionar al menos 1 foto"
            return
        }
    }


}