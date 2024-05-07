package com.example.smarttrade.catalogue.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smarttrade.catalogue.viewmodel.Product


@Composable
fun guardarMasTarde() {
    Text(text = "Lista de deseados")
    Spacer(modifier = Modifier.height(10.dp))
    val listadeseados :List<Product>  = objetcLists.ListaDeseados.getItems()
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

