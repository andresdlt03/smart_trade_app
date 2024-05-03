package com.example.smarttrade.catalogue.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smarttrade.auth.domain.model.User

open class catalogueViewModel : ViewModel() {

    protected val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    protected val _filterCategory = MutableLiveData<Boolean>()
    val filterCategory: LiveData<Boolean> = _filterCategory

    protected val _filterPrice = MutableLiveData<Boolean>()
    val filterPrice: LiveData<Boolean> = _filterPrice

    protected val _filterC = MutableLiveData<Boolean>()
    val filterC: LiveData<Boolean> = _filterC

    protected val _filterP = MutableLiveData<Boolean>()
    val filterP: LiveData<Boolean> = _filterP

    open fun activeFilterC(){
        _filterC.value = true
    }

    open fun activeFilterP(){
        _filterP.value = true
    }

    open fun unActiveFilterC(){
        _filterC.value = false
    }

    open fun unActiveFilterP(){
        _filterP.value = false
    }


    protected val _technologyChecked = MutableLiveData(false)
    protected val _booksChecked = MutableLiveData(false)
    protected val _clothingChecked = MutableLiveData(false)
    protected val _foodChecked = MutableLiveData(false)

    val technologyChecked: LiveData<Boolean> = _technologyChecked
    val booksChecked: LiveData<Boolean> = _booksChecked
    val clothingChecked: LiveData<Boolean> = _clothingChecked
    val foodChecked: LiveData<Boolean> = _foodChecked

    open fun searchChanged(it: String){
        _search.value = it
    }

    open fun clearSelected(){
        _search.value = ""
    }

    open fun setTechnologyChecked(checked: Boolean) {
        _technologyChecked.value = checked
    }

    open fun setBooksChecked(checked: Boolean) {
        _booksChecked.value = checked
    }

    open fun setClothingChecked(checked: Boolean) {
        _clothingChecked.value = checked
    }

    open fun setFoodChecked(checked: Boolean) {
        _foodChecked.value = checked
    }

    open fun activeFilterCategory(){
        _filterCategory.value = true
    }

    open fun activeFilterPrice(){
        _filterPrice.value = true
    }

    open fun unActiveFilterCategory(){
        _filterCategory.value = false
    }

    open fun unActiveFilterPrice(){
        _filterPrice.value = false
    }

    open fun cancelFilterCategory(){
        _technologyChecked.value = false
        _booksChecked.value = false
        _clothingChecked.value = false
        _foodChecked.value = false
    }

    protected val _minPrice = MutableLiveData<Float>()
    val minPrice: LiveData<Float> = _minPrice

    protected val _maxPrice = MutableLiveData<Float>()
    val maxPrice: LiveData<Float> = _maxPrice

    protected val _activePrice = MutableLiveData<Boolean>()
    val activePrice: LiveData<Boolean> = _activePrice

    open fun setMinPrice(price: Float) {
        _minPrice.value = price
        _activePrice.value = true
    }

    open fun setMaxPrice(price: Float) {
        _maxPrice.value = price
        _activePrice.value = true

    }

    open fun disablePriceFilter(){
        _activePrice.value = false
        _maxPrice.value = 1000F
        _minPrice.value = 0F
    }

    val priceRange: ClosedFloatingPointRange<Float> = 0f..1000f

    protected var _product: Product? = null
    protected var _user: User? = null

    open fun setProduct(uri: Uri?, name: String, price: String, description: String, cat: String) {
        _product = Product(uri, name, price, description, cat)
    }

    open fun getProduct(): Product? {
        return _product
    }


    protected val _filteredProduct = MutableLiveData<List<Product>>()
    val filteredProduct : LiveData<List<Product>> = _filteredProduct

    var listaCatalogo: MutableList<Product> = mutableListOf()

    open fun getLista():MutableList<Product> {
        return listaCatalogo
    }

    open fun returnCategoriesChecked(): String{
        var aux: String  = ""
        if(technologyChecked.value != null && technologyChecked.value == true){
            aux = aux + ("Tecnología")
        }
        if(booksChecked.value != null && booksChecked.value == true){
            aux = aux + ("Libros")
        }
        if(foodChecked.value != null && foodChecked.value == true){
            aux = aux + ("Comida")
        }
        if(clothingChecked.value != null && clothingChecked.value == true){
            aux = aux + ("Ropa")
        }
        return aux
    }
}

data class Product(
    val uri: Uri?,
    val name: String,
    val price: String,
    val description: String,
    val category : String
)