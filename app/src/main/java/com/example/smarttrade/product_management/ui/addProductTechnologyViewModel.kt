package com.example.smarttrade.product_management.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class `addProductTechnologyViewModel` : ViewModel(){

    // id = 1
    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    // id = 2
    private val _description = MutableLiveData<String>()
    val description : LiveData<String> = _description

    // id = 3
    private val _model = MutableLiveData<String>()
    val model : LiveData<String> = _model

    // id = 4
    private val _energy = MutableLiveData<String>()
    val energy : LiveData<String> = _energy

    // id = 5
    private val _price = MutableLiveData<String>()
    val price : LiveData<String> = _price

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _name.value = item
            2 ->    _description.value = item
            3 ->    _model.value = item
            4 ->    _energy.value = item
            5 ->    _price.value = item
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _name.value = ""
            2 ->    _description.value = ""
            3 ->    _model.value = ""
            4 ->    _energy.value = ""
            5 ->    _price.value = ""
        }
    }

    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("main")
    }
}