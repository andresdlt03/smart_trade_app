package com.example.smarttrade.product_management.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class `addProductBooksViewModel` : ViewModel(){

    // id = 1
    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    // id = 2
    private val _description = MutableLiveData<String>()
    val description : LiveData<String> = _description

    // id = 3
    private val _isbn = MutableLiveData<String>()
    val isbn : LiveData<String> = _isbn

    // id = 4
    private val _price = MutableLiveData<String>()
    val price : LiveData<String> = _price

    fun onItemChanged(item :String, id: Int){
        when(id){
            1 ->    _name.value = item
            2 ->    _description.value = item
            3 ->    _isbn.value = item
            4 ->    _price.value = item
        }
    }

    fun clearSelected(id: Int){
        when(id){
            1 ->    _name.value = ""
            2 ->    _description.value = ""
            3 ->    _isbn.value = ""
            4 ->    _price.value = ""
        }
    }
}