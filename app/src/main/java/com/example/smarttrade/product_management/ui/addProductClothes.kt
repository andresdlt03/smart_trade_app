package com.example.smarttrade.product_management.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun addProductClothesScreen(viewModel: addProductClothesViewModel, navHostController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        addProductClothes(viewModel, navHostController)
    }
}

@Composable
fun addProductClothes(viewModel: addProductClothesViewModel, navHostController: NavHostController){

    val name :String by viewModel.name.observeAsState(initial = "")
    val description :String by viewModel.description.observeAsState(initial = "")
    val size :String by viewModel.size.observeAsState(initial = "")
    val price :String by viewModel.price.observeAsState(initial = "")

    topBarAdd({viewModel.goBackCategories(navHostController)},navHostController)
    Spacer(modifier = Modifier.height(24.dp))
    outLinedTextAdd(name, "Nombre (20 carácteres)", 1,{viewModel.onItemChanged(it,1)} ,{ viewModel.clearSelected(1) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(description, "Descripción (50 carácteres)", 2,{viewModel.onItemChanged(it,2)} ,{ viewModel.clearSelected(2) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(size, "Talla (Tallaje español)", 3,{viewModel.onItemChanged(it,3)} ,{ viewModel.clearSelected(3) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(price, "Precio del producto", 4,{viewModel.onItemChanged(it,4)} ,{ viewModel.clearSelected(4) })
    Spacer(modifier = Modifier.height(54.dp))
    addImage()
    Spacer(modifier = Modifier.height(54.dp))
    publishProductButton()
}





