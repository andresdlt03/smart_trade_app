package com.example.smarttrade.catalogue.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.R

@Composable
fun viewProductCatalogueScreen(
    viewModel: viewProductCatalogueViewModel,
    navControler: NavHostController,
    viewModel2: mainCatalogueViewModel
){
    Column {
        viewProductCatalogue(viewModel, navControler, viewModel2)
    }
}

@Composable
fun viewProductCatalogue(viewModel: viewProductCatalogueViewModel,navControler :NavHostController, viewModel2: mainCatalogueViewModel) {
    val producto: Product? = viewModel2.getProduct()
    if(producto != null) {
        topBarManage(producto.name, navControler)
        Spacer(modifier = Modifier.height(20.dp))
        ProductoEnPantalla(imagenResId = producto.IdImage, nombre = producto.name, precio = producto.price, descripcion = producto.description)
    }
}



@Composable
fun topBarManage(text : String, navControler: NavHostController){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ){
        FloatingActionButton(
            onClick = { navControler.navigate("catalogue")},
        ) {
            Icon(Icons.Filled.ArrowBack, "Back button")
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = text,
            fontSize = 22.sp
        )
    }
}


@Composable
fun ProductoEnPantalla(
    imagenResId: Int,
    nombre: String,
    precio: String,
    descripcion: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imagenResId),
            contentDescription = null,
            modifier = Modifier.size(width = 200.dp, height = 200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = nombre,
            style = TextStyle(fontSize = 24.sp),
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Precio: $precio €",
            style = TextStyle(fontSize = 15.sp),
            color = Color.Yellow,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = descripcion,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}
