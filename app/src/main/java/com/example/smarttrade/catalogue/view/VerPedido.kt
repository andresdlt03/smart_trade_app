package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderScreen(
    navHostController: NavHostController
) {
    Scaffold(
        Modifier
            .background(
                color = Color.White
            ), containerColor = Color.White,

        bottomBar = {
            BottomAppBar {

            }

        }
    ){

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* VOLVER */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }

                Text(
                    "Informacion del pedido",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}


@Composable
fun ViewItem(
    items: List<@Composable () -> Unit>,
    secondTitle: String,
    orderNumber: String,
    orderDate: String,
    totalAmount: String,
    paymentMethod: String,

    ){
    Column() {
        Text(secondTitle, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            items.forEach { item ->
                item()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Número de pedido: #$orderNumber", fontSize = 16.sp)
        Text("Fecha: $orderDate", fontSize = 16.sp)
        Text("Total: $totalAmount", fontSize = 16.sp)
        Text("Forma de pago: $paymentMethod", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        items.forEach { item ->
            Text("$item" /*ITEM.CANTIDAD*/ + " x " +  "$item" /*ITEM.NOMBRE*/, fontSize = 16.sp)
        }

        Button(
            onClick = { /* Acción del botón */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cancelar pedido")
        }
    }
}