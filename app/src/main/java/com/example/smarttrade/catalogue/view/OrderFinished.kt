package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.R
import com.example.smarttrade.singleton.UserLogged

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderFinishedScreen(navHostController: NavHostController) {
    Scaffold(
        Modifier
            .background(
                color = Color.White
            ), containerColor = Color.White,

        bottomBar = {
            when (UserLogged.userType) {
                "seller" -> sellerBottomBar(navHostController)
                "client" -> clientBottomBar(navHostController)
                "admin" -> adminBottomBar(navHostController)
                else -> throw IllegalArgumentException("Tipo de usuario desconocido")
            }

        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "¡Gracias! \uD83C\uDF89", fontSize = 30.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text("¡Tu orden ha sido recibida!", fontSize = 30.sp)

            Spacer(modifier = Modifier.height(32.dp))

            // Llamada a la lista horizontal
            val items = listOf(
                ItemData(painterResource(R.drawable.mobile_image), "1"),
                ItemData(painterResource(R.drawable.book_image), "2"),
                ItemData(painterResource(R.drawable.apple_image), "10"),
                ItemData(painterResource(R.drawable.book_image), "23"),
                ItemData(painterResource(R.drawable.mobile_image), "1"),
                ItemData(painterResource(R.drawable.apple_image), "23")
            )
            HorizontalListWithImagesAndText(items = items)

            Spacer(modifier = Modifier.height(32.dp))

            // Detalles del pedido
            OrderDetails(
                orderNumber = "#8",
                date = "Junio 20, 2024",
                total = "$57.00",
                paymentMethod = "Tarjeta de crédito"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navHostController.navigate("catalogue") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Volver al Inicio")
            }
        }
    }
}

@Composable
fun OrderDetails(orderNumber: String, date: String, total: String, paymentMethod: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Order details",
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        )
        TextRow(label = "Número de pedido:", value = orderNumber)
        TextRow(label = "Fecha:", value = date)
        TextRow(label = "Total:", value = total)
        TextRow(label = "Forma de pago:", value = paymentMethod)


    }
}

@Composable
fun TextRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun HorizontalListWithImagesAndText(items: List<ItemData>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = item.image,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.text)
            }
        }
    }
}

data class ItemData(val image: Painter, val text: String)

