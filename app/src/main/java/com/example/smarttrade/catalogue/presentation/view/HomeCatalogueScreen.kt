package com.example.smarttrade.catalogue.presentation.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.catalogue.data.repository.ProductWrapper
import com.example.smarttrade.catalogue.presentation.viewmodel.HomeCatalogueViewModel
import com.example.smarttrade.components.BottomBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCatalogueScreen(
    viewModel: HomeCatalogueViewModel = hiltViewModel(),
    navController: NavHostController,
    scrollState: ScrollState
) {

    val state = viewModel.state.collectAsState().value

    // Initialize the lists of products each time the catalogue is recomposed
    LaunchedEffect(Unit) {
        viewModel.initializeProducts()
    }

    Scaffold (
        modifier = Modifier
            .background(color = Color.White),
        containerColor = Color.White,
        bottomBar =  {
            BottomBar(navController = navController)
        }
    ) {
        padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
            ) {
                SearchBar(
                    query = state.search,
                    onQueryChange = { viewModel.updateSearch(it) },
                    onSearch = { viewModel.updateSearch(it) },
                    active = state.search.isNotEmpty(),
                    onActiveChange = {  },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search icon"
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear search icon",
                            Modifier.clickable { viewModel.clearSearch() }
                        )
                    }
                ) {
                    state.products?.let {
                        ProductsList(
                            productWrappers = it,
                            searchFilter = state.search,
                        )
                    }
                }
            }
    }
}

@Composable
fun ProductsList(
    productWrappers: List<ProductWrapper>,
    searchFilter: String,
    // more filters
) {
    Column {
        productWrappers
            .filter { it.product.name.contains(searchFilter, ignoreCase = true) }
            .forEach { productWrapper ->
                ProductItem(
                    image = productWrapper.product.photo,
                    name = productWrapper.product.name,
                    price = productWrapper.product.price.toString(),
                    description = productWrapper.product.description
                )
            }
    }
}

@Composable
fun ProductItem(
    image: ByteArray?,
    name: String,
    price: String,
    description: String
) {
    Row {
        // Image(painter = , contentDescription = )
        Column {
            Text(text = name)
            Text(text = price)
            Text(text = description)
        }
    }
}