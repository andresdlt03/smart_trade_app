package com.example.smarttrade.product_management.presentation.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TopBar(goBackCategories:(NavHostController) -> Unit, navController: NavHostController){
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