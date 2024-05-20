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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.smarttrade.catalogue.data.remote.http.lists.CarritoListRequest
import com.example.smarttrade.catalogue.data.remote.http.lists.GuardarTardeListRequest
import com.example.smarttrade.catalogue.viewmodel.ListaCarritoViewModel
import com.example.smarttrade.catalogue.viewmodel.ListaDeseadosViewModel
import com.example.smarttrade.catalogue.viewmodel.Product
import com.example.smarttrade.singleton.UserLogged
import java.text.DecimalFormat


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun carritoCompra(navController : NavHostController, scrollState: ScrollState, viewModel:ListaCarritoViewModel) {
        val typeUser = UserLogged.userType
        viewModel.getListaCarrito()
        viewModel.getListaGuardarTarde()
        Scaffold (
            modifier = Modifier
                .background(color = Color.White
                ),
            containerColor = Color.White,

            bottomBar =  {
                when (typeUser){
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

            val listaCarrito :List<ListaCarritoViewModel.ProductListaCarrito> by viewModel.listacarrito.observeAsState(initial =
            listOf())

            val listaguardartarde :List<ListaCarritoViewModel.ProductListaGuardarTarde> by viewModel.listaguardartarde.observeAsState(initial =
            listOf())

            val carritoTotalPrice: String by viewModel.carritoTotalPrice.observeAsState("")

            val carritoiva: String by viewModel.carritoiva.observeAsState("")


            for (i in listaCarrito) {
                ProductItemListaCarrito(
                    nombre = i.name,
                    precio = i.price,
                    descripcion = i.description,
                    stock = i.stock,
                    seller = i.seller
                )
                Row {
                    Button(onClick = {
                        viewModel.deleteFromCarritoList(CarritoListRequest(
                            sellerEmail = i.seller,
                            productId = i.name,
                            quantity = i.stock
                        ))                    }) {
                        Text(text = "Eliminar")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = {
                        viewModel.deleteFromCarritoList(
                            CarritoListRequest(
                            sellerEmail = i.seller,
                            productId = i.name,
                            quantity = i.stock
                        ))
                        viewModel.addToGuardarTardeList(
                            GuardarTardeListRequest(
                            sellerEmail = i.seller,
                            productId = i.name
                        ))
                    }) {
                        Text("Mover a Mas tarde")
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "El IVA del carrito de compra es : $carritoiva€")
            Text(text = "El precio total del carrito de compra es : $carritoTotalPrice")
            Button(onClick = {
                { /* PAGINA DE PAGAR*/ }
            }) {
                Text(text = "Pagar")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Guardar para más tarde", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))

            for (i in listaguardartarde) {
                ProductItemListaGuardarTarde(
                    nombre = i.name,
                    precio = i.price,
                    descripcion = i.description,
                    seller = i.seller
                )

                Button(onClick = {
                    viewModel.deleteFromGuardarTardeList(GuardarTardeListRequest(
                        productId = i.name,
                        sellerEmail = i.seller
                    ));
                    viewModel.addToCarritoList(
                        CarritoListRequest(
                            productId = i.name,
                            sellerEmail = i.seller,
                            quantity = "1"
                        )
                    )
                }) {
                    Text("Mover a carrito")
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
        }
}
@Composable
fun ProductItemListaCarrito(
    nombre : String,
    precio: String,
    descripcion: String,
    stock: String,
    seller: String,
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
            Text(text = "Stock: $stock", fontSize = 14.sp)

        }
    }
}

@Composable
fun ProductItemListaGuardarTarde(
    nombre : String,
    precio: String,
    descripcion: String,
    seller: String
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
