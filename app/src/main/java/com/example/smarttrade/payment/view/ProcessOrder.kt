package com.example.smarttrade.payment.view

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.lists.CartState
import com.example.smarttrade.orders.Order
import com.example.smarttrade.orders.OrdersState
import com.example.smarttrade.singleton.UserLogged
import java.time.LocalDate
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProcessOrder(
    navController : NavHostController,
    scrollState: ScrollState
) {
    var deliveryAddress by remember { mutableStateOf(UserLogged.dir) }
    var isEditingAddress by remember { mutableStateOf(false) }
    var paymentMethod by remember { mutableStateOf("Tarjeta de crédito") }

    var orderDone by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
             TopAppBar(
                 title = { Text(text = "Procesar pedido", fontSize = 24.sp) },
                 navigationIcon = {
                     IconButton(onClick = { navController.navigate(NavRoutes.SHOPPING_CART.route) }) {
                         Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                     }
                 }
             )
        },
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxHeight(),
        containerColor = Color.White,
        bottomBar =  {
            BottomAppBar {}
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 60.dp)
        ) {

            Text(text = "Dirección de entrega", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = deliveryAddress,
                    onValueChange = { deliveryAddress = it },
                    enabled = isEditingAddress,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .border(1.dp, if (isEditingAddress) Color.Black else Color.Gray)
                        .padding(8.dp),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
                )
                Button(onClick = { isEditingAddress = !isEditingAddress }) {
                    Text(text = if (isEditingAddress) "Guardar" else "Cambiar")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Información de contacto", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Nombre completo: ${UserLogged.fullName}", fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp))
            Text(text = "Correo electrónico: ${UserLogged.email}", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Método de pago", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = paymentMethod == "Tarjeta de crédito",
                        onClick = { paymentMethod = "Tarjeta de crédito" }
                    )
                    Text(text = "Tarjeta de crédito", fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = paymentMethod == "Paypal",
                        onClick = { paymentMethod = "Paypal" }
                    )
                    Text(text = "Paypal", fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Total\t${CartState.getCartTotal()} €")
            }

            Button(
                    onClick = {
                        OrdersState.orders.add(
                            Order(
                                CartState.getCart().map {
                                    "${it.quantity} x ${it.productName}"
                                }.toMutableList(),
                                Random.nextInt(100000, 1000000).toString(),
                                LocalDate.now().toString(),
                                CartState.getCartTotal(),
                                paymentMethod
                            )
                        )
                        CartState.clearCart()
                        orderDone = true
                    },
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .align(Alignment.End)

                ) {
                    Text(text = "Pagar", color = Color.White)
                }
        }
    }

    if (orderDone) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                Button(
                    onClick = {
                        navController.navigate(NavRoutes.HOME.route)
                        orderDone = false
                    }
                )
                { Text(text = "Seguir comprando") }
                            },
            text = { Text(text = "Pedido realizado con éxito") }
        )
    }
}
