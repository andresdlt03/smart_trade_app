package com.example.smarttrade.product_management.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.product_management.presentation.viewmodel.Category
import com.example.smarttrade.product_management.presentation.viewmodel.ProductManagementViewModel


@Composable
fun ProductManagementScreen(viewModel: ProductManagementViewModel = hiltViewModel(), navController: NavHostController) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ){
        ProductManagement(viewModel, navController)
    }
}

@Composable
fun ProductManagement(viewModel: ProductManagementViewModel = hiltViewModel(),
                      navController: NavHostController){

    val category :String by viewModel.category.observeAsState(initial = "")

    val filteredCategories :List<Category> by viewModel.filteredCategories.observeAsState(initial =
        listOf(
            Category("Tecnología", Icons.Filled.Build),
            Category( "Libros", Icons.Filled.Email),
            Category("Comida", Icons.Filled.Home),
            Category("Ropa", Icons.Filled.Face)
        )
    )

    topBarManage(navController)
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextManage(category,{viewModel.onCategoryChanged(it)}, {viewModel.clearSelected()})
    Spacer(modifier = Modifier.height(14.dp))
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
    Spacer(modifier = Modifier.height(14.dp))
    CategoryItems(filteredCategories, navController, viewModel)
}



@Composable
fun topBarManage(navController: NavHostController){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        FloatingActionButton(
            onClick = {navController.navigate(NavRoutes.HOME.route) },
        ) {
            Icon(Icons.Filled.ArrowBack, "Back button")
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "Selecciona una categoría",
            fontSize = 22.sp
        )
    }
}


@Composable
fun outLinedTextManage(category: String, onCategoryChanged:(String) -> Unit, clearSelected:() -> Unit){
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
                modifier = Modifier.clickable{
                    clearSelected()
                }
            )
        },
        value = category,
        label = { Text("Buscar una categoría") },
        onValueChange = {onCategoryChanged(it)}
    )
}

@Composable
fun CategoryItems(filteredCategories: List<Category>, navControler: NavHostController, viewModel: ProductManagementViewModel){
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp)
    )
    {
        for ( category in filteredCategories)
            ListItem(category = category, navControler, viewModel)
    }
}

@Composable
fun ListItem(category: Category, navControler: NavHostController, viewModel: ProductManagementViewModel){
    ListItem(
        colors = ListItemDefaults.colors(containerColor = Color(android.graphics.Color.parseColor("#FF3F8B4A"))),
        headlineContent = { Text(text = category.name) },
        leadingContent = {
            Icon(
                category.icon,
                contentDescription = category.name
            )
        },
        modifier = Modifier.clickable(onClick = { viewModel.changeAddScreen(navControler,category.name) })
    )
}




