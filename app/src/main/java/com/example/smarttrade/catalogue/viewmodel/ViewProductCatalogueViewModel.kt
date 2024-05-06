package com.example.smarttrade.catalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import com.example.smarttrade.network.Exception.NetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class viewProductCatalogueViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository,
): ViewModel(){
    fun aproveProduct(name:String){



    }

}