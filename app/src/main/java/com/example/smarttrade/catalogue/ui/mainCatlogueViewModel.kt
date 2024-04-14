package com.example.smarttrade.catalogue.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class mainCatalogueViewModel : ViewModel() {

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _selectedCategories = MutableLiveData<MutableList<String>>()
    val selectedCategories: LiveData<MutableList<String>> = _selectedCategories

    private val _filterCatgegory = MutableLiveData<Boolean>()
    val filterCatgegory: LiveData<Boolean> = _filterCatgegory

    private val _filterPrice = MutableLiveData<Boolean>()
    val filterPrice: LiveData<Boolean> = _filterPrice

    fun activeFilterCategory(){
        _filterCatgegory.value = true
    }

    fun activeFilterPrice(){
        _filterPrice.value = true
    }

    fun unActiveFilterCategory(){
        _filterCatgegory.value = false
    }

    fun unActiveFilterPrice(){
        _filterPrice.value = false
    }
}