package com.example.smarttrade.catalogue.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class mainCatalogueViewModel : ViewModel() {

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _filterCatgegory = MutableLiveData<Boolean>()
    val filterCatgegory: LiveData<Boolean> = _filterCatgegory

    private val _filterPrice = MutableLiveData<Boolean>()
    val filterPrice: LiveData<Boolean> = _filterPrice

    private val _technologyChecked = MutableLiveData(false)
    private val _booksChecked = MutableLiveData(false)
    private val _clothingChecked = MutableLiveData(false)
    private val _foodChecked = MutableLiveData(false)

    val technologyChecked: LiveData<Boolean> = _technologyChecked
    val booksChecked: LiveData<Boolean> = _booksChecked
    val clothingChecked: LiveData<Boolean> = _clothingChecked
    val foodChecked: LiveData<Boolean> = _foodChecked

    fun searchChanged(it: String){
        _search.value = it
    }

    fun clearSelected(){
        _search.value = ""
    }

    fun setTechnologyChecked(checked: Boolean) {
        _technologyChecked.value = checked
    }

    fun setBooksChecked(checked: Boolean) {
        _booksChecked.value = checked
    }

    fun setClothingChecked(checked: Boolean) {
        _clothingChecked.value = checked
    }

    fun setFoodChecked(checked: Boolean) {
        _foodChecked.value = checked
    }

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

    fun cancelFilterCategory(){
        _technologyChecked.value = false
        _booksChecked.value = false
        _clothingChecked.value = false
        _foodChecked.value = false
    }

    private val _minPrice = MutableLiveData<Float>()
    val minPrice: LiveData<Float> = _minPrice

    private val _maxPrice = MutableLiveData<Float>()
    val maxPrice: LiveData<Float> = _maxPrice

    private val _activePrice = MutableLiveData<Boolean>()
    val activePrice: LiveData<Boolean> = _activePrice

    fun setMinPrice(price: Float) {
        _minPrice.value = price
        _activePrice.value = true
    }

    fun setMaxPrice(price: Float) {
        _maxPrice.value = price
        _activePrice.value = true

    }

    fun disablePriceFilter(){
        _activePrice.value = false
        _maxPrice.value = 1000F
        _minPrice.value = 0F
    }

    val priceRange: ClosedFloatingPointRange<Float> = 0f..1000f

    private var _product: Product? = null

    fun setProduct(IdImage: Int, name: String, price: String, description: String) {
        _product = Product(IdImage, name, price, description)
    }

    fun getProduct(): Product? {
        return _product
    }

}

data class Product(
    val IdImage: Int,
    val name: String,
    val price: String,
    val description: String
)