package com.example.smarttrade.product_management.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductManagementViewModel @Inject constructor() : ViewModel(){

    private val categories = listOf(
        Category("Tecnología", Icons.Filled.Build),
        Category( "Libros", Icons.Filled.Email),
        Category("Comida", Icons.Filled.Home),
        Category("Ropa", Icons.Filled.Face)
    )

    private val _category = MutableLiveData<String>()
    val category : LiveData<String> = _category

    fun onCategoryChanged(category :String){
        if(category.trim() == ""){
            _category.value = category
            _filteredCategories.value = categories
        }else {
            _category.value = category
            _filteredCategories.value = categories.filter {
                it.name.contains(category, ignoreCase = true)
            }
        }
    }

    fun clearSelected(){
        _category.value = ""
        _filteredCategories.value = categories
    }

    private val _filteredCategories = MutableLiveData<List<Category>>()
    val filteredCategories : LiveData<List<Category>> = _filteredCategories

    fun changeAddScreen(navControler: NavHostController, name: String){
        when(name){
            "Tecnología" -> navControler.navigate(NavRoutes.ADD_TECHNOLOGY.route)
            "Libros" ->     navControler.navigate(NavRoutes.ADD_BOOKS.route)
            "Comida" ->     navControler.navigate(NavRoutes.ADD_FOOD.route)
            "Ropa"->        navControler.navigate(NavRoutes.ADD_CLOTHES.route)
        }

    }
}

data class Category(
    val name: String,
    val icon: ImageVector
)