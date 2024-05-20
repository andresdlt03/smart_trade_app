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
import com.example.smarttrade.catalogue.data.remote.http.lists.WishListRequest
import com.example.smarttrade.catalogue.viewmodel.ListaDeseadosViewModel
import com.example.smarttrade.singleton.UserLogged


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaDeseosScreen(navController: NavHostController, scrollState: ScrollState, viewModel: ListaDeseadosViewModel) {
    val typeUser = UserLogged.userType
    viewModel.getListaDeseados()

    Scaffold (
        modifier = Modifier
            .background(color = Color.White
            ),
        containerColor = Color.White,

        bottomBar =  {
            //bottomBar
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
            Text(text = "Lista de Deseos", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            val listadeseados :List<ListaDeseadosViewModel.ProductListaDeseados> by viewModel.listadeseados.observeAsState(initial =
            listOf())
            for (i in listadeseados) {

                ProductItemListaDeseados(
                    nombre = i.name,
                    precio = i.price,
                    descripcion = i.description,
                    seller = i.seller
                )
                Row {
                    Button(onClick = {
                        viewModel.deleteFromWishList(
                            WishListRequest(
                                productId = i.name,
                                sellerEmail = i.seller
                            )
                        )
                    }) {
                        Text(text = "Eliminar")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = {
                        viewModel.deleteFromWishList(
                            WishListRequest(
                                productId = i.name,
                                sellerEmail = i.seller
                            )
                        );
                        { /*poner en el carrito*/ }
                    }) {
                        Text("Mover a Carrito")
                    }
                }
            }
        }

    }
}


@Composable
fun ProductItemListaDeseados(
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
