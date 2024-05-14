package com.example.smarttrade.catalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class listaCarritoViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository,
): ViewModel() {


    private val _listacarrito = MutableLiveData<List<Product>>()
    val listacarrito: LiveData<List<Product>> = _listacarrito
    var listaCarrito: MutableList<Product> = mutableListOf()


    


}