package com.example.smarttrade.catalogue.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel

@Composable
fun mainCatalogueScreen(viewModel: productManagementViewModel){
    Column {
        mainCatalogue(viewModel)
    }
}

@Composable
fun mainCatalogue(viewModel: productManagementViewModel){
    val search :String by viewModel.search.observeAsState(initial = "")
}

@Preview
@Composable
fun outLinedTextManage(){
    val search = ""
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
                modifier = Modifier.clickable{}

            )
        },
        value = search,
        label = { Text("Buscar un producto") },
        onValueChange = {}
    )
}
