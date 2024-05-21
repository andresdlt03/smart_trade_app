package com.example.smarttrade.catalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.catalogue.data.remote.http.lists.WishListRequest
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import com.example.smarttrade.network.Exception.NetworkException
import com.example.smarttrade.singleton.UserLogged
import com.google.gson.Gson
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ListaDeseadosViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository,
): ViewModel() {


    private val _listadeseados = MutableLiveData<List<ProductListaDeseados>>()
    val listadeseados: LiveData<List<ProductListaDeseados>> = _listadeseados


    fun getListaDeseados(){
        viewModelScope.launch {
            try {
                val call = catalogueRepository.getList("wishlist")
                if(call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString -> _listadeseados.value = parseJsonListaDeseados(jsonString)}
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }

    }

    fun deleteFromWishList(Product: WishListRequest){
        viewModelScope.launch {
            try {
                var userLoggedEmail = UserLogged.email
                val call = catalogueRepository.deleteFromWishList(Product, userLoggedEmail)
                if(call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString -> _listadeseados.value = parseJsonListaDeseados(jsonString)}
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }
    }

    fun addToWishList(Product: WishListRequest){
        viewModelScope.launch {
            try {
                var userLoggedEmail = UserLogged.email
                val call = catalogueRepository.addToWishList(Product, userLoggedEmail)
                if(call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString -> _listadeseados.value = parseJsonListaDeseados(jsonString)}
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }
    }

    data class ProductListaDeseados(
        val name: String,
        val description: String,
        val dataSheet: String,
        val category: String,
        val price: String,
        val seller : String,
    )

    fun parseJsonListaDeseados(jsonString: String): List<ProductListaDeseados> {
        val gson = Gson()
        val productsJsonArray = gson.fromJson(jsonString, Array<JsonElement>::class.java)

        return productsJsonArray.map { jsonElement ->
            val productPrice = jsonElement.asJsonObject.get("price").asString
            val productSeller = jsonElement.asJsonObject.get("seller").asString
            val productJsonObject = jsonElement.asJsonObject.get("product").asJsonObject

            ProductListaDeseados(
                name = productJsonObject.get("name").asString,
                description = productJsonObject.get("description").asString,
                dataSheet = productJsonObject.get("dataSheet").asString,
                category = productJsonObject.get("category").asString,
                price = productPrice,
                seller = productSeller
            )
        }
    }
}