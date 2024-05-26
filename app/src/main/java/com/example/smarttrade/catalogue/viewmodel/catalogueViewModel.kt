package com.example.smarttrade.catalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.auth.domain.model.User
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import com.example.smarttrade.network.Exception.NetworkException
import com.google.gson.Gson
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class catalogueViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository,
): ViewModel(){

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    private val _filterCategory = MutableLiveData<Boolean>()
    val filterCategory: LiveData<Boolean> = _filterCategory

    private val _filterPrice = MutableLiveData<Boolean>()
    val filterPrice: LiveData<Boolean> = _filterPrice

    private val _filterC = MutableLiveData<Boolean>()
    val filterC: LiveData<Boolean> = _filterC

    private val _filterP = MutableLiveData<Boolean>()
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

    private var _product: Product? = null
    private var _user: User? = null

    open fun getProduct(): Product? {
        return _product
    }

    fun setProduct(p: Product){
        _product = p
    }


    private val _filteredProduct = MutableLiveData<List<Product>>()
    val filteredProduct : LiveData<List<Product>> = _filteredProduct


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


    fun getVerifiedProducts(){

        viewModelScope.launch {
            try {
                val call = catalogueRepository.getVerifiedProducts()
                if(call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString -> _filteredProduct.value = parseJson(jsonString)}

                } else {
                    val body = call.errorBody()?.string()
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }

    }

    fun getUnverifiedProducts(){

        viewModelScope.launch {
            try {
                val call = catalogueRepository.getUnverifiedProducts()
                if(call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString -> _filteredProduct.value = parseJson(jsonString)}
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }

    }
}

data class Product(
    val name: String,
    val description: String,
    val dataSheet: String,
    val verified: Boolean,
    val category: String,
    val additionalFields: List<String> = emptyList(),
    val price: String
)

fun parseJson(jsonString: String): List<Product> {
    val gson = Gson()
    val productsJsonArray = gson.fromJson(jsonString, Array<JsonElement>::class.java)

    return productsJsonArray.map { jsonElement ->
        val productCategory = jsonElement.asJsonObject.get("category").asString
        val productJsonObject = jsonElement.asJsonObject.get("product").asJsonObject

        Product(
            name = productJsonObject.get("name").asString,
            description = productJsonObject.get("description").asString,
            dataSheet = productJsonObject.get("dataSheet").asString,
            verified = productJsonObject.get("verified").asBoolean,
            price = productJsonObject.get("price").asString,
            category = productCategory,
        )
    }
}