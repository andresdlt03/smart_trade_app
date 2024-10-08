package com.example.smarttrade.catalogue.presentation.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
                ) {}
                state.products?.let {
                    ProductsList(
                        viewModel = viewModel,
                        productWrappers = it,
                        searchFilter = state.search,
                    )
                }
            }
    }

    // Open selected product

    state.selectedProduct?.let {
        AlertDialog(onDismissRequest = { /*TODO*/ }) {
            ProductDetailsScreen(
                navController = navController,
                product = it
            )
        }
    }
}

@Composable
fun ProductsList(
    viewModel: HomeCatalogueViewModel,
    productWrappers: List<ProductWrapper>,
    searchFilter: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        productWrappers
            .filter { it.product.name.contains(searchFilter, ignoreCase = true) }
            .forEach { productWrapper ->
                ProductItem(
                    product = productWrapper,
                    viewModel = viewModel
                )
            }
    }
}

