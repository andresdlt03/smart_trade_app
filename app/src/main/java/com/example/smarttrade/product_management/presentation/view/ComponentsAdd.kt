package com.example.smarttrade.product_management.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.viewmodel.catalogueViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductBookViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductClothesViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductFoodViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductTechnologyViewModel


@Composable
fun topBarAdd(goBackCategories:(NavHostController) -> Unit , navController: NavHostController){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { goBackCategories(navController)},
        ) {
            Icon(Icons.Filled.ArrowBack, "Back button")
        }
        Spacer(modifier = Modifier.width(34.dp))
        Text(
            text = "Datos del producto",
            fontSize = 22.sp
        )
    }
}

@Composable
fun text(text: String){
    Text(text = text)
}

@Composable
fun outLinedTextAdd(item: String, text: (String), id: Int, onItemChanged:(String) -> Unit, clearSelected:(Int) -> Unit){


    OutlinedTextField(
        modifier = Modifier
            .clickable {

            }
            .fillMaxWidth(),

        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .clickable {
                        clearSelected(id)
                    },
            )
        },
        value = item,
        label = { Text(text = text) },
        onValueChange = { onItemChanged(it) }
    )
}

@Composable
fun addImage(){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = { },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_foto),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Añade una foto del producto")
        }
    }
}

@Composable
fun publishProductButton1(viewModel: AddProductTechnologyViewModel, vm: catalogueViewModel, navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                viewModel.checkAllVariables()
                if(viewModel.error() == false){
                    viewModel.addProduct(vm)
                    navController.navigate("catalogue")

                }

            })
        {
            Text(text = "Publicar producto")
        }
    }
}

@Composable
fun publishProductButton2(viewModel: AddProductFoodViewModel, vm: catalogueViewModel, navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                viewModel.checkAllVariables()
                if(viewModel.error() == false){
                    viewModel.addProduct(vm)
                    navController.navigate("catalogue")

                }

            })
        {
            Text(text = "Publicar producto")
        }
    }
}

@Composable
fun publishProductButton3(viewModel: AddProductClothesViewModel, vm: catalogueViewModel, navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                viewModel.checkAllVariables()
                if(viewModel.error() == false){
                    viewModel.addProduct(vm)
                    navController.navigate("catalogue")

                }

            })
        {
            Text(text = "Publicar producto")
        }
    }
}

@Composable
fun publishProductButton4(viewModel: AddProductBookViewModel, vm: catalogueViewModel, navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                viewModel.checkAllVariables()
                if(viewModel.error() == false){
                    viewModel.addProduct(vm)
                    navController.navigate("catalogue")
                }

            })
        {
            Text(text = "Publicar producto")
        }
    }
}
