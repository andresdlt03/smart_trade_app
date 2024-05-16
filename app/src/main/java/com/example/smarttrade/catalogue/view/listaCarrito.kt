package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.viewmodel.Product
import com.example.smarttrade.singleton.UserLogged
import java.text.DecimalFormat
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun carritoCompra(navController : NavHostController, scrollState: ScrollState) {

    val userType = UserLogged.userType

    Scaffold (
        modifier = Modifier
            .background(color = Color.White
            ),
        containerColor = Color.White,

        bottomBar =  {
            when (userType){
                "seller" -> sellerBottomBar(navController)
                "client" ->  clientBottomBar(navController)
                "admin" -> adminBottomBar(navController)
                else -> throw IllegalArgumentException("Tipo de usuario desconocido")
            }

        }
    ){
    Column (
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(scrollState)){

        Text(text = "Carrito de la Compra", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))

        val listaCarrito :List<Product>  = objetcLists.ListaCarrito.getItems()
        val listaguardarTarde : List<Product> = objetcLists.ListaGuardarTarde.getItems()
        var totalPrice = 0

        for (i in listaCarrito) {
            totalPrice = totalPrice + i.price.toInt()
            ProductItem2(
                nombre = i.name,
                precio = i.price,
                descripcion = i.description,
                cat = i.category,
                product = i
            )
            Row {
                RemoveItemButton(
                    sourceListName = moveItemButton.carrito,
                    item = i,
                    navController = navController
                )

                Spacer(modifier = Modifier.width(20.dp))

                MoveItemButton(
                    sourceListName = moveItemButton.carrito,
                    destinationListName = moveItemButton.masTarde,
                    item = i,
                    navController = navController
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        val aux = totalPrice / 1.21
        val df = DecimalFormat("#.##")
        df.maximumFractionDigits = 2
        val IVA = df.format(aux)

        Text(text = "El IVA del carrito de compra es : $IVA€")
        Text(text = "El precio total del carrito de compra es : $totalPrice€")

        Button(onClick = {
            objetcLists.ListaCarrito.clearItems();
            navController.navigate("shoppingCart")
        }) {
            Text(text = "Pagar")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Guardar para más tarde", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(10.dp))

        for (i in listaguardarTarde) {
            ProductItem2(
                nombre = i.name,
                precio = i.price,
                descripcion = i.description,
                cat = i.category,
                product = i
            )

            Row {
                RemoveItemButton(
                    sourceListName = moveItemButton.masTarde,
                    item = i,
                    navController = navController
                )

                Spacer(modifier = Modifier.width(20.dp))

                MoveItemButton(
                    sourceListName = moveItemButton.masTarde,
                    destinationListName = moveItemButton.carrito,
                    item = i,
                    navController = navController
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
        }
    }
}



@Composable
fun ProductItem2(
    nombre : String,
    precio: String,
    descripcion: String,
    cat : String,
    product : Product
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
            }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_product),
            contentDescription = null,
            modifier = Modifier
                .clickable { /* acción al hacer clic */ }
                .size(80.dp, 80.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Precio: $precio €" , fontSize = 14.sp)
            Text(text = "Descripción: $descripcion", fontSize = 14.sp)
        }
    }
}