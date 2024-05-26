package com.example.smarttrade.product_management.presentation.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductViewModel

@Composable
fun PublishProductButton(viewModel: AddProductViewModel, navController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                viewModel.publishProduct()
            })
        {
            Text(text = "Publicar producto")
        }
    }
}
