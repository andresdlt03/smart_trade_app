package com.example.smarttrade.lists.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.R
import com.example.smarttrade.components.BottomBar
import com.example.smarttrade.lists.CartState
import com.example.smarttrade.lists.viewmodel.state.CartItem

@Composable
fun CartScreen(
    navController: NavHostController
) {

    val state = CartState

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){
        padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Elementos en el carrito: ${state.getCartSize()}",
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            state.getCart().forEach { item ->
                CartElement(
                    item = item,
                    navController = navController
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Total: ",
                    textAlign = TextAlign.End,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "€ ${state.getCartTotal()}",
                    textAlign = TextAlign.End,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold

                )
            }

            Button(
                modifier = Modifier
                    .align(Alignment.End),
                enabled = state.getCartSize() > 0,
                onClick = {
                    navController.navigate(NavRoutes.CHECKOUT.route)
                }
            ) {
                Text(text = "Tramitar pedido")
            }
        }
    }
}

@Composable
fun CartElement(
    item: CartItem,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .size(68.dp),
                contentDescription = "Imagen"
            )

            Column {
                Text(
                    text = item.productName,
                    fontSize = 20.sp
                )
                Text(
                    text = "€ ${item.price} (por unidad)",
                    fontSize = 16.sp
                )
                Text(
                    text = "Cantidad: ${item.quantity}",
                    fontSize = 16.sp
                )
            }
        }

        IconButton(onClick = {
            CartState.removeFromCart(item)
            navController.navigate(NavRoutes.SHOPPING_CART.route)
        }) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = Color.Red,
                contentDescription = "Remove"
            )
        }
    }
}