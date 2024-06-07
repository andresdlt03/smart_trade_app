package com.example.smarttrade.catalogue.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.data.repository.ProductWrapper
import com.example.smarttrade.catalogue.presentation.viewmodel.ProductDetailsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavHostController,
    scrollState: ScrollState,
    product: ProductWrapper
) {
    Scaffold (
        topBar = { TopAppBar(
            title = { Text(
                fontSize = 20.sp,
                text = "Detalles del producto"
            ) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
            }
        ) },
        modifier = Modifier
            .background(color = Color.White),
        containerColor = Color.White,
    ) {
        padding ->
        Column(
            modifier = Modifier
                .padding(32.dp)
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .size(200.dp),
                contentDescription = "Logo",
            )

            Column(
                modifier = Modifier
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = product.product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Text(
                    text = product.product.price.toString() + " €",
                    fontSize = 24.sp
                )
                Text(
                    text = product.product.description,
                    fontSize = 24.sp
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ){
                    Column {
                        Text(
                            text = "Stock: ",
                            fontSize = 22.sp
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp),
                            textStyle = TextStyle(fontSize = 18.sp),
                            value = "0",
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = {
                                viewModel.updateStockSelected(it.toInt())
                            },
                        )
                    }
                    Button(
                        onClick = {
                            // TODO: Implementar lógica para agregar a la lista de deseos
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                        )
                    }
                }
                Button(
                    onClick = {
                        // TODO: Implementar lógica para agregar al carrito
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
                        Text(
                            text = "Agregar al carrito",
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PriceSellerDropdown(
    initialPrice: String,
    initialSeller: String,
    otherPriceSellers: List<String>,
    menuBackgroundColor: Color = Color.Gray // Puedes cambiar el color según tu preferencia
) {
    var selectedPriceSeller by remember { mutableStateOf("$initialPrice - $initialSeller") }
    var expanded by remember { mutableStateOf(false) }

    val allPriceSellers = mutableListOf("$initialPrice - $initialSeller").apply {
        addAll(otherPriceSellers)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = selectedPriceSeller, color = Color.White)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon", tint = Color.White)
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(menuBackgroundColor)
            ) {
                allPriceSellers.forEach { priceSeller ->
                    DropdownMenuItem(
                        onClick = {
                            selectedPriceSeller = priceSeller
                            expanded = false
                        },
                        text = { Text(text = priceSeller) }
                    )
                }
            }
        }
    }
}