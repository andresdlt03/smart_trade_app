package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.catalogue.viewmodel.Product
import com.example.smarttrade.singleton.UserLogged


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaDeseosScreen(navController: NavHostController, scrollState: ScrollState) {
    val typeUser = UserLogged.userType

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
            val listadeseados: List<Product> = objetcLists.ListaDeseados.getItems()

            for (i in listadeseados) {

                ProductItem2(
                    nombre = i.name,
                    precio = i.price,
                    descripcion = i.description,
                    cat = i.category,
                    product = i
                )
                Row {

                    RemoveItemButton(
                        sourceListName = moveItemButton.deseado,
                        item = i,
                        navController =navController
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    MoveItemButton(
                        sourceListName = moveItemButton.deseado,
                        destinationListName = moveItemButton.carrito,
                        item = i,
                        navController = navController
                    )
                }
            }
        }

    }
}

