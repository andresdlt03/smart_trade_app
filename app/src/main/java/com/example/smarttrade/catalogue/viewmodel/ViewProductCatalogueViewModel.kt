package com.example.smarttrade.catalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class viewProductCatalogueViewModel @Inject constructor(
    catalogueRepository: CatalogueRepository,
): ViewModel(){
    fun aproveProduct(name:String){

    }
}