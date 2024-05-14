package com.example.smarttrade.catalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttrade.catalogue.domain.repository.CatalogueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewProductCatalogueViewModel @Inject constructor(
    private val catalogueRepository: CatalogueRepository,
): ViewModel() {
    fun verifyProduct(productName: String) {
        viewModelScope.launch {
            catalogueRepository.verifyProduct(productName)
        }
    }
}