package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.smarttrade.catalogue.viewmodel.Product
import com.example.smarttrade.singleton.UserLogged

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProcessOrder(navController : NavHostController, scrollState: ScrollState) {

    Scaffold (
        Modifier
            .background(color = Color.White
            ), containerColor = Color.White,

        bottomBar =  {
            when (UserLogged.userType){
                "seller" -> sellerBottomBar(navController)
                "client" ->  clientBottomBar(navController)
                "admin" -> adminBottomBar(navController)
                else -> throw IllegalArgumentException("Tipo de usuario desconocido")
            }

        }
    ) {
        var deliveryAddress by remember { mutableStateOf(UserLogged.dir) }
        var isEditingAddress by remember { mutableStateOf(false) }
        var paymentMethod by remember { mutableStateOf("Tarjeta de crédito") }

        Column(
            modifier = Modifier
                .padding(10.dp)
                .verticalScroll(scrollState)
        ) {

            Row{
                GoBackButton(previousPageName = Buttons.carrito, navController = navController)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Tramitando pedido", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

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
        }
    }
}

