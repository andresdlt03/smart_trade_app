package com.example.smarttrade.catalogue.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.data.repository.ProductWrapper
import com.example.smarttrade.catalogue.presentation.viewmodel.ProductDetailsViewModel
import com.example.smarttrade.singleton.UserLogged


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    navController: NavHostController,
    product: ProductWrapper
) {

    var addedToCartDialog by remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.initializeAvailabilities(product.product.name)
    }

    Scaffold (
        topBar = { TopAppBar(
            title = { Text(
                fontSize = 20.sp,
                text = "Detalles del producto"
            ) },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(NavRoutes.HOME.route) }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
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
                .padding(top = 48.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .size(120.dp),
                contentDescription = "Logo",
            )

            Column(
                modifier = Modifier
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = product.product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = product.product.price.toString() + " €",
                    fontSize = 18.sp
                )
                Text(
                    text = product.product.description,
                    fontSize = 16.sp
                )
            }

            // Change the controls depending on the user type
            UserLogged.userType?.let {
                if (it == "client") {
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(28.dp)
                    ){

                        PriceSellerDropdown(
                            otherPriceSellers = state.availabilitiesString,
                            viewModel
                        )

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(32.dp)
                        ){
                            Column {
                                Text(
                                    text = "Cantidad: ",
                                    fontSize = 12.sp
                                )
                                OutlinedTextField(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .height(60.dp),
                                    textStyle = TextStyle(fontSize = 18.sp),
                                    value = state.stockSelected,
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    onValueChange = {
                                        viewModel.updateStockSelected(it)
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

                                viewModel.addProductToCart(
                                    product.product.name,
                                    state.priceSelected,
                                    state.stockSelected.toInt()
                                )
                                addedToCartDialog = true
                            }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
                                Text(
                                    text = "Agregar al carrito",
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                } else if (it == "seller") {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 24.dp),
                    ) {
                        Button(
                            onClick = {
                                viewModel.deleteProductAvailability(product.product.name)
                                navController.navigate(NavRoutes.HOME.route)
                                },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {
                            Text(
                                text = "Borrar producto",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }

        }
    }

    // Added to Cart Dialog
    if (addedToCartDialog) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        addedToCartDialog = false
                        navController.navigate(NavRoutes.HOME.route)
                    }
                ) {
                    Text(text = "Seguir comprando")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        addedToCartDialog = false
                        navController.navigate(NavRoutes.SHOPPING_CART.route)
                    }
                ) {
                    Text(text = "Ir al carrito")
                }
            },
            text = {
                Text(
                    text = "Producto añadido al carrito",
                    fontSize = 20.sp
                )
            },
        )
    }
}
@Composable
fun PriceSellerDropdown(
    otherPriceSellers: List<String>,
    viewModel: ProductDetailsViewModel
) {
    var selectedPriceSeller by remember { mutableStateOf(otherPriceSellers.firstOrNull() ?: "") }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(250.dp)
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = selectedPriceSeller, color = Color.Black)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon", tint = Color.Black)
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
                    .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
            ) {
                otherPriceSellers.forEach { priceSeller ->
                    DropdownMenuItem(
                        onClick = {
                            selectedPriceSeller = priceSeller
                            viewModel.updatePriceSelected(priceSeller.split(" - ")[1].split(" €")[0].toDouble())
                            expanded = false
                        },
                        text = { Text(text = priceSeller) }
                    )
                }
            }
        }
    }
}