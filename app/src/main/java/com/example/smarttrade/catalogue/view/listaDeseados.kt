package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smarttrade.catalogue.viewmodel.Product
import com.example.smarttrade.singleton.UserLogged


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaDeseosScreen(navController: NavHostController) {
    val typeUser = UserLogged.usertype
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
        Text(text = "Lista de deseados")
        Spacer(modifier = Modifier.height(10.dp))
        val listadeseados: List<Product> = objetcLists.ListaDeseados.getItems()
        var precio_total = 0
        for (i in listadeseados) {
            precio_total = precio_total + i.price.toInt()
            ProductItem2(
                nombre = i.name,
                precio = i.price,
                descripcion = i.description,
                cat = i.category,
                product = i
            )
        }
    }
}

