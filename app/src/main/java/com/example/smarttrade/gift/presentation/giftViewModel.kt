package com.example.smarttrade.gift.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class giftViewModel : ViewModel() {

    fun goBackCategories(navController: NavHostController){
        navController.navigate("catalogue")
    }

    private val _lists = MutableLiveData<List<String>>()
    val lists : LiveData<List<String>> = _lists

    var listas: MutableList<String> = mutableListOf()

    private val _selectedList = MutableLiveData<String>()
    val selectedList : LiveData<String> = _selectedList

    private val _activatePop = MutableLiveData<Boolean>()
    val activatePop : LiveData<Boolean> = _activatePop

    fun addStringToList(newString: String) {
        listas.add(newString)
        _lists.value = listas
    }

    fun unActivePop(){
        _activatePop.value = false
    }

    fun activePop(){
        _activatePop.value = true
    }

    fun changeSelectedList(string: String){
        _selectedList.value = string
    }

    fun getFirstofList(): String{
        return if(!listas.isEmpty()){
            listas[0]
        } else{
            ""
        }
    }
}
