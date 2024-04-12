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

@Composable
fun addProductBooksScreen(viewModel: addProductBooksViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        addProductBooks(viewModel)
    }
}

@Composable
fun addProductBooks(viewModel: addProductBooksViewModel){

    val name :String by viewModel.name.observeAsState(initial = "Nombre (20 carácteres)")
    val description :String by viewModel.description.observeAsState(initial = "Descripción (50 carácteres)")
    val isbn :String by viewModel.isbn.observeAsState(initial = "ISBN")
    val price :String by viewModel.price.observeAsState(initial = "Precio del producto")

    topBarAdd()
    Spacer(modifier = Modifier.height(24.dp))
    outLinedTextAdd(name, "Nombre", 1,{viewModel.onItemChanged(it,1)} ,{ viewModel.clearSelected(1) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(description, "Descripción", 2,{viewModel.onItemChanged(it,2)} ,{ viewModel.clearSelected(2) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(isbn, "ISBN", 3,{viewModel.onItemChanged(it,3)} ,{ viewModel.clearSelected(3) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(price, "Precio", 4,{viewModel.onItemChanged(it,4)} ,{ viewModel.clearSelected(4) })
    Spacer(modifier = Modifier.height(54.dp))
    addImage()
    Spacer(modifier = Modifier.height(54.dp))
    publishProductButton()
}


@Preview
@Composable
fun preview4(){
    val addProductBooksViewModel : `addProductBooksViewModel`
    addProductBooksScreen(viewModel = `addProductBooksViewModel`())
}


