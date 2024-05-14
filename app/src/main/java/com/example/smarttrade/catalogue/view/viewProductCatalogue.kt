package com.example.smarttrade.catalogue.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.viewmodel.Product
import com.example.smarttrade.catalogue.viewmodel.catalogueViewModel
import com.example.smarttrade.catalogue.viewmodel.viewProductCatalogueViewModel
import com.example.smarttrade.product_management.presentation.view.text
import com.example.smarttrade.singleton.UserLogged

@Composable
fun viewProductCatalogueScreen(
    viewModel: viewProductCatalogueViewModel = hiltViewModel(),
    navController: NavHostController,
    viewModel2: catalogueViewModel
){
    Column {
        viewProductCatalogue(viewModel, navController, viewModel2)
    }
}

@Composable
fun viewProductCatalogue(viewModel: viewProductCatalogueViewModel, navControler :NavHostController, viewModel2: catalogueViewModel) {
    val producto: Product? = viewModel2.getProduct()
    if(producto != null) {
        topBarManage(producto.category, navControler)
        Spacer(modifier = Modifier.height(20.dp))
        ProductoEnPantalla( name = producto.name, price = producto.price, descripcion = producto.description, p = producto, viewModel2)
        Spacer(modifier = Modifier.height(20.dp))
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
    name: String,
    price: String,
    descripcion: String,
    p : Product,
    vm : catalogueViewModel
) {
    var alertPressed by remember { mutableStateOf(false)}
    var number by remember { mutableStateOf(1)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.default_product),
            contentDescription = null,
            modifier = Modifier
                .clickable { /* acción al hacer clic */ }
                .size(80.dp, 80.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = name,
            style = TextStyle(fontSize = 24.sp),
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Precio: $price €",
            style = TextStyle(fontSize = 15.sp),
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = descripcion,
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {alertPressed = !alertPressed},
            content = {
                text(text = "Ver datasheet")
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        NumberSelector(number) { updatedNumber ->
            number = updatedNumber
        }

        when(UserLogged.userType){
            "client" -> clientViewProduct(vm, number)
            "seller" -> sellerViewProduct()
            "admin" -> adminViewProduct()
        }

        if(alertPressed){
            datasheet(p.dataSheet, {alertPressed = !alertPressed})
        }
    }
}
@Composable
fun NumberSelector(initialNumber: Int, onNumberChanged: (Int) -> Unit) {
    var number by remember { mutableStateOf(initialNumber) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = {
            number--
            onNumberChanged(number)
        }) {
            Text(text = "-")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = number.toString())
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            number++
            onNumberChanged(number)
        }) {
            Text(text = "+")
        }
    }
}


@Composable
fun datasheet(text: String?, onClose: () -> Unit) {
    if (text != null) {
        AlertDialog(
            onDismissRequest = onClose,
            title = {
                Text(text = "Información del producto")
            },
            text = {
                Text(text = text)
            },
            confirmButton = {
                Button(
                    onClick = onClose,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}