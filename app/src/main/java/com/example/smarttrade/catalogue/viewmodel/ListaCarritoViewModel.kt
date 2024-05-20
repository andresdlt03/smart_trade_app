package com.example.smarttrade.catalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.catalogue.data.remote.http.lists.CarritoListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.GuardarTardeListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.WishListRequest
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import com.example.smarttrade.network.Exception.NetworkException
import com.example.smarttrade.singleton.UserLogged
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
open class ListaCarritoViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository,
): ViewModel() {

    private val _listacarrito = MutableLiveData<List<ProductListaCarrito>>()
    val listacarrito: LiveData<List<ProductListaCarrito>> = _listacarrito


    private val _listaguardartarde = MutableLiveData<List<ProductListaGuardarTarde>>()
    val listaguardartarde: LiveData<List<ProductListaGuardarTarde>> = _listaguardartarde


    private val _carritoTotalPrice = MutableLiveData<String>()
    val carritoTotalPrice: LiveData<String> = _carritoTotalPrice

    private val _carritoiva = MutableLiveData<String>()
    val carritoiva: LiveData<String> = _carritoiva

    fun getListaCarrito() {
        viewModelScope.launch {
            try {
                val call = catalogueRepository.getList("shoppingcart ")
                if(call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString -> _listacarrito.value = parseJsonListaCarrito(jsonString)}
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }
    }

    fun getListaGuardarTarde() {
        viewModelScope.launch {
            try {
                val call = catalogueRepository.getList("savedforlater")
                if(call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString -> _listaguardartarde.value = parseJsonListaGuardarTarde(jsonString)}
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch(e: NetworkException) {
                println(e.message)
            }
        }
    }

    fun deleteFromCarritoList(Product: CarritoListRequest) {
        viewModelScope.launch {
            try {
                var userLoggedEmail = UserLogged.email
                val call = catalogueRepository.deleteFromCarritoList(Product, userLoggedEmail)
                if (call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString ->
                        _listacarrito.value = parseJsonListaCarrito(jsonString)
                    }
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch (e: NetworkException) {
                println(e.message)
            }
        }
    }

    fun addToCarritoList(Product: CarritoListRequest) {
        viewModelScope.launch {
            try {
                var userLoggedEmail = UserLogged.email
                val call = catalogueRepository.addToCarritoList(Product, userLoggedEmail)
                if (call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString ->
                        _listacarrito.value = parseJsonListaCarrito(jsonString)
                    }
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch (e: NetworkException) {
                println(e.message)
            }
        }
    }

    fun deleteFromGuardarTardeList(Product: GuardarTardeListRequest) {
        viewModelScope.launch {
            try {
                var userLoggedEmail = UserLogged.email
                val call = catalogueRepository.deleteFromGuardarTardeList(Product, userLoggedEmail)
                if (call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString ->
                        _listaguardartarde.value = parseJsonListaGuardarTarde(jsonString)
                    }
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch (e: NetworkException) {
                println(e.message)
            }
        }
    }

    fun addToGuardarTardeList(Product: GuardarTardeListRequest) {
        viewModelScope.launch {
            try {

                var userLoggedEmail = UserLogged.email
                val call = catalogueRepository.addToGuardarTardeList(Product, userLoggedEmail)
                if (call.isSuccessful) {
                    val responseBody = call.body()
                    responseBody?.let { jsonString ->
                        _listaguardartarde.value = parseJsonListaGuardarTarde(jsonString)
                    }
                } else {
                    val body = call.errorBody()?.string()
                }
            } catch (e: NetworkException) {
                println(e.message)
            }
        }
    }

    fun parseJsonListaCarrito(jsonString: String): List<ProductListaCarrito> {
        val gson = Gson()
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)

        val cartPrice = jsonObject.get("cartPrice").asString
        _carritoTotalPrice.value = cartPrice
        val iva = jsonObject.get("IVA").asString
        _carritoiva.value = iva

        val cartProducts = jsonObject.getAsJsonArray("cartProducts").map { jsonElement ->
            val productJsonObject = jsonElement.asJsonObject

            val product = productJsonObject.getAsJsonObject("product")
            val seller = productJsonObject.get("seller").asString
            val price = productJsonObject.get("price").asString
            val stock = productJsonObject.get("stock").asString

            ProductListaCarrito(
                name = product.get("name").asString,
                description = product.get("description").asString,
                category = product.get("category").asString,
                price = price,
                seller = seller,
                stock = stock
            )
        }
        return cartProducts
    }

    fun parseJsonListaGuardarTarde(jsonString: String): List<ProductListaGuardarTarde> {
        val gson = Gson()
        val productsJsonArray = gson.fromJson(jsonString, Array<JsonElement>::class.java)

        return productsJsonArray.map { jsonElement ->
            val productPrice = jsonElement.asJsonObject.get("price").asString
            val productSeller = jsonElement.asJsonObject.get("seller").asString
            val productJsonObject = jsonElement.asJsonObject.get("product").asJsonObject

            ProductListaGuardarTarde(
                name = productJsonObject.get("name").asString,
                description = productJsonObject.get("description").asString,
                dataSheet = productJsonObject.get("dataSheet").asString,
                category = productJsonObject.get("category").asString,
                price = productPrice,
                seller = productSeller
            )
        }
    }

    data class ProductListaCarrito(
        val name: String,
        val description: String,
        val category: String,
        val price: String,
        val seller: String,
        val stock: String
    )

    data class ProductListaGuardarTarde(
        val name: String,
        val description: String,
        val dataSheet: String,
        val category: String,
        val price: String,
        val seller : String,
    )


}