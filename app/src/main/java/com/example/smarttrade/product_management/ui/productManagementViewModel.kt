package com.example.smarttrade.product_management.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class productManagementViewModel : ViewModel(){

    private val categories = listOf(
        Category("Tecnología", Icons.Filled.Build),
        Category( "Libros", Icons.Filled.Email),
        Category("Comida", Icons.Filled.Home),
        Category("Ropa", Icons.Filled.Face)
    )

    private val _category = MutableLiveData<String>()
    val category : LiveData<String> = _category

    fun onCategoryChanged(category :String){
        _category.value = category
        _filteredCategories.value = categories.filter {
            it.name.contains(category, ignoreCase = true)
        }
    }

    fun clearSelected(){
        _category.value = ""
    }

    private val _filteredCategories = MutableLiveData<List<Category>>()
    val filteredCategories : LiveData<List<Category>> = _filteredCategories
}

data class Category(
    val name: String,
    val icon: ImageVector
)