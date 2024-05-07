package com.example.smarttrade.catalogue.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.viewmodel.Product


@Composable
fun carritoCompra() {

    Text(text = "Carrito de la Compra")
    Spacer(modifier = Modifier.height(10.dp))
    val listaCarrito :List<Product>  = objetcLists.ListaCarrito.getItems()
    val listaguardarTarde : List<Product> = objetcLists.ListaGuardarTarde.getItems()
    var precio_total = 0
    for (i in listaCarrito) {
        precio_total = precio_total + i.price.toInt()
        ProductItem2(
            nombre = i.name,
            precio = i.price,
            descripcion = i.description,
            cat = i.category,
            product = i
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "El precio total del carrito de compra es : {precio_total}")
    Spacer(modifier = Modifier.height(30.dp))
    Text(text = "Guardar para más tarde")
    Spacer(modifier = Modifier.height(10.dp))

    for (i in listaguardarTarde) {
        ProductItem2(
            nombre = i.name,
            precio = i.price,
            descripcion = i.description,
            cat = i.category,
            product = i
        )
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
