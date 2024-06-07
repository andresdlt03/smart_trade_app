package com.example.smarttrade.orders.OrdersHistory

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.components.BottomBar
import com.example.smarttrade.orders.OrderDetails.OrderDetailsScreen
import com.example.smarttrade.orders.OrdersState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrdersHistoryScreen(
    viewModel: OrdersHistoryViewModel = hiltViewModel(),
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
                    paymentMethod = order.paymentMethod,
                    navHostController = navHostController,
                    viewModel = viewModel
                )
            }
        }
    }

    if(viewModel.orderSelected.value != null){
        AlertDialog(onDismissRequest = { /*TODO*/ }) {
            OrderDetailsScreen(
                order = viewModel.orderSelected.value!!,
                navHostController = navHostController
            )
        }
    }
}


@Composable
fun ViewItems(
    orderNumber: String,
    orderDate: String,
    totalAmount: Double,
    paymentMethod: String,
    navHostController: NavHostController,
    viewModel: OrdersHistoryViewModel
    ){

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Text("Número de pedido: $orderNumber", fontSize = 16.sp)
                Text("Fecha: $orderDate", fontSize = 16.sp)
                Text("Total: $totalAmount", fontSize = 16.sp)
                Text("Forma de pago: $paymentMethod", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.updateOrderSelected(OrdersState.orders.first { it.orderNumber == orderNumber })
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Visualizar pedido")
            }
        }
    }
}