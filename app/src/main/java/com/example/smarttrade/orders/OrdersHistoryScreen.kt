package com.example.smarttrade.orders

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
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
import com.example.smarttrade.components.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrdersHistoryScreen(
    navHostController: NavHostController
) {
    Scaffold(
        Modifier
            .background(
                color = Color.White
            ), containerColor = Color.White,

        bottomBar = {
            BottomBar(navController = navHostController)

        }
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* VOLVER */} ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                }

                Text(
                    "Historial de pedidos",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            OrdersState.orders.forEach { order ->
                ViewItems(
                    orderNumber = order.orderNumber,
                    orderDate = order.orderDate,
                    totalAmount = order.totalAmount,
                    paymentMethod = order.paymentMethod
                )
            }
        }
    }
}


@Composable
fun ViewItems(
    orderNumber: String,
    orderDate: String,
    totalAmount: Double,
    paymentMethod: String
    ){
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Text("Número de pedido: $orderNumber", fontSize = 16.sp)
            Text("Fecha: $orderDate", fontSize = 16.sp)
            Text("Total: $totalAmount", fontSize = 16.sp)
            Text("Forma de pago: $paymentMethod", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Acción del botón */ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Visualizar pedido")
            }
        }
    }
}