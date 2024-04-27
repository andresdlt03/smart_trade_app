package com.example.smarttrade.gift.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.catalogue.ui.Product
import javax.inject.Inject

class giftViewModel : ViewModel() {


    fun goBackCategories(navControler: NavHostController){
        navControler.navigate("catalogue")
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
        return listas.get(0)
    }
}
