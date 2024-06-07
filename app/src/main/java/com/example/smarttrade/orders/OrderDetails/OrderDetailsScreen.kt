package com.example.smarttrade.orders.OrderDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.orders.Order
import com.example.smarttrade.orders.OrdersState
import com.example.smarttrade.ratings.RatingScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderDetailsScreen(
    order: Order,
    navHostController: NavHostController
) {
    Scaffold (
        topBar = { TopAppBar(
            title = { Text(
                fontSize = 20.sp,
                text = "Detalles del pedido"
            ) },
            navigationIcon = {
                IconButton(onClick = { navHostController.navigate(NavRoutes.ORDERS_HISTORY.route) }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        ) },
        modifier = Modifier
            .background(color = Color.White),
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 60.dp)
        ) {
            ViewItem(
                items = order.items,
                orderNumber = order.orderNumber,
                orderDate = order.orderDate,
                totalAmount = order.totalAmount,
                paymentMethod = order.paymentMethod,
                status = order.status,
                navHostController = navHostController
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewItem(
    items: MutableList<String>,
    orderNumber: String,
    orderDate: String,
    totalAmount: Double,
    paymentMethod: String,
    status: String,
    navHostController: NavHostController
){

    var canceledOrder by remember { mutableStateOf(false) }
    var ratingProduct by remember { mutableStateOf(false) }

    Column {
        Row {
            Text(
                text = "Estado del pedido: ",
                fontSize = 16.sp,
                color = Color.Red
            )
            Text(
                text = status,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text("Número de pedido: #$orderNumber", fontSize = 16.sp)
        Text("Fecha: $orderDate", fontSize = 16.sp)
        Text("Total: $totalAmount €", fontSize = 16.sp)
        Text("Forma de pago: $paymentMethod", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        items.forEach { item ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(item, fontSize = 16.sp)
                Text(
                    text = "Dejar una reseña",
                    modifier = Modifier
                        .clickable {
                            ratingProduct = true
                        },
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }

        if(status == "Realizado"){

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                          OrdersState.orders.replaceAll {
                              if (it.orderNumber == orderNumber) {
                                  it.status = "Cancelado"
                              }
                              it
                          }
                    canceledOrder = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )

            ) {
                Text("Cancelar pedido")
            }
        }
    }

    if(canceledOrder){
        AlertDialog(
            onDismissRequest = {
                canceledOrder = false
                navHostController.navigate(NavRoutes.ORDERS_HISTORY.route)
            },
            confirmButton = {
                Button(
                    onClick = {
                        canceledOrder = false
                        navHostController.navigate(NavRoutes.ORDERS_HISTORY.route)
                    }
                ) {
                    Text("Volver a mis pedidos")
                }
            },
            text = {
                Text("El pedido ha sido cancelado con éxito")
            }

        )
    }

    if(ratingProduct) {
        AlertDialog(onDismissRequest = { /*TODO*/ }) {
            RatingScreen(navHostController = navHostController)
        }
    }
}