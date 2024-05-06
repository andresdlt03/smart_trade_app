package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
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
import com.example.smarttrade.auth.presentation.viewmodel.LoginViewModel
import com.example.smarttrade.auth.sigleton.SingletonValue
import com.example.smarttrade.catalogue.viewmodel.Product
import com.example.smarttrade.catalogue.viewmodel.catalogueViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.Category

@Composable
fun mainCatalogueScreen(
    // user: User,
    viewModel: catalogueViewModel,
    navController: NavHostController,
    scrollState: ScrollState,
    loginViewModel: LoginViewModel
){
Column {
        mainCatalogue(viewModel, navController, scrollState, loginViewModel)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainCatalogue(
    // user: User,
    viewModel: catalogueViewModel,
    navController: NavHostController,
    scrollState: ScrollState,
    loginViewModel: LoginViewModel
){

    val search :String by viewModel.search.observeAsState(initial = "")
    val typeUser = SingletonValue.value

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
    )
    {
        outLinedTextManage(
            search = search,
            activeFilterCategory = { viewModel.activeFilterCategory() },
            activeFilterPrice = { viewModel.activeFilterPrice() },
            UnActiveFilterCategory = { viewModel.unActiveFilterCategory() },
            UnActiveFilterPrice = { viewModel.unActiveFilterPrice() },
            viewModel = viewModel,
            navController,
            scrollState = scrollState
        )
    }
}

@Composable
fun outLinedTextManage(
    search: String,
    activeFilterCategory:() -> Unit,
    activeFilterPrice:() -> Unit,
    UnActiveFilterCategory:() -> Unit,
    UnActiveFilterPrice:() -> Unit,
    viewModel: catalogueViewModel,
    navController: NavHostController,
    scrollState: ScrollState

){
    val filteredProducts :List<Product> by viewModel.filteredProduct.observeAsState(initial =
    listOf())
    val filterCategory :Boolean by viewModel.filterCategory.observeAsState(initial = false)
    val filterPrice:Boolean by viewModel.filterPrice.observeAsState(initial = false)

    val filterC :Boolean by viewModel.filterC.observeAsState(initial = false)
    val filterP:Boolean by viewModel.filterP.observeAsState(initial = false)

    val search : String by viewModel.search.observeAsState(initial = "")

    listOf(
        Category("Tecnología", Icons.Filled.Build),
        Category( "Libros", Icons.Filled.Email),
        Category("Comida", Icons.Filled.Home),
        Category("Ropa", Icons.Filled.Face)
    )

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable {viewModel.clearSelected()}

                )
            },
            value = search,
            label = { Text("Buscar un producto") },
            onValueChange = {viewModel.searchChanged(it)}
        )
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {activeFilterPrice()}) {
                Text("Filtrar por precio")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { activeFilterCategory()}) {
                Text("Filtrar por categoría")
            }
        }
        if(filterCategory == true) {

            val technologyChecked by viewModel.technologyChecked.observeAsState(false)
            val booksChecked by viewModel.booksChecked.observeAsState(false)
            val clothingChecked by viewModel.clothingChecked.observeAsState(false)
            val foodChecked by viewModel.foodChecked.observeAsState(false)

            AlertDialog(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),

                onDismissRequest = { UnActiveFilterCategory() },
                title = {
                    Row(modifier = Modifier.padding(horizontal = 16.dp) ){
                    Text(
                    text = "Filtrar por categoría")
                        }},
                confirmButton = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                    Button(
                        onClick = { UnActiveFilterCategory();
                        if(viewModel.returnCategoriesChecked() != "")
                            viewModel.activeFilterC()},
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                    ) {
                        Text("Aceptar")
                    }}
                },
                dismissButton = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                    Button(
                        onClick = {
                            UnActiveFilterCategory()
                            viewModel.cancelFilterCategory()
                            viewModel.unActiveFilterC()
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp),

                    ) {
                        Text("Cancelar")
                    }}
                },
                text = {
                    Column {
                        CheckBoxItem(
                            text = "Tecnología",
                            checked = technologyChecked,
                            onCheckedChange = { viewModel.setTechnologyChecked(it) }
                        )


                        CheckBoxItem(
                            text = "Libros",
                            checked = booksChecked,
                            onCheckedChange = { viewModel.setBooksChecked(it) }
                        )


                        CheckBoxItem(
                            text = "Ropa",
                            checked = clothingChecked,
                            onCheckedChange = { viewModel.setClothingChecked(it) }
                        )


                        CheckBoxItem(
                            text = "Comida",
                            checked = foodChecked,
                            onCheckedChange = { viewModel.setFoodChecked(it) }
                        )
                    }
                }
            )

        }

            if(filterPrice == true){
            val minPrice by viewModel.minPrice.observeAsState(0F)
            val maxPrice by viewModel.maxPrice.observeAsState(1000F)

            AlertDialog(

                onDismissRequest = { UnActiveFilterPrice() },
                title = { Text("Filtrar por precio") },
                confirmButton = {
                    Button(
                        onClick = { UnActiveFilterPrice();viewModel.activeFilterP() } ,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                    ) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { viewModel.disablePriceFilter();UnActiveFilterPrice();viewModel.unActiveFilterP()},
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                    ) {
                        Text("Cancelar")
                    }
                },
                text ={
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        minPrice?.let { currentMinPrice ->
                            Text("Precio mínimo: %.1f".format(currentMinPrice) + " €")
                            Slider(
                                value = currentMinPrice,
                                onValueChange = { viewModel.setMinPrice(it) },
                                valueRange = viewModel.priceRange,
                                steps = 1000,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        maxPrice?.let { currentMaxPrice ->
                            Text("Precio máximo: %.1f".format(currentMaxPrice) + " €")
                            Slider(
                                value = currentMaxPrice,
                                onValueChange = { viewModel.setMaxPrice(it) },
                                valueRange = viewModel.priceRange,
                                steps = 1000,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                }

            )
        }

        val minPrice by viewModel.minPrice.observeAsState(0F)
        val maxPrice by viewModel.maxPrice.observeAsState(1000F)

        for (i in filteredProducts){
            if(i.name.contains(search.trim())) {
                if (filterC && filterP) {
                    val m: String = viewModel.returnCategoriesChecked()
                    if (i.category in m && i.price.toFloatOrNull()!! >= minPrice && i.price.toFloatOrNull()!! <= maxPrice) {
                        ProductItem(
                            viewModel = viewModel,
                            navController = navController,
                            nombre = i.name,
                            precio = i.price,
                            descripcion = i.description,
                            cat = i.category
                        )
                    }
                } else if (filterC && !filterP) {
                    val m: String = viewModel.returnCategoriesChecked()
                    if (i.category in m) {
                        ProductItem(
                            viewModel = viewModel,
                            navController = navController,
                            nombre = i.name,
                            precio = i.price,
                            descripcion = i.description,
                            cat = i.category
                        )
                    }
                } else if (filterC && !filterP) {
                    if (i.price.toFloatOrNull()!! >= minPrice && i.price.toFloatOrNull()!! <= maxPrice) {
                        ProductItem(
                            viewModel = viewModel,
                            navController = navController,
                            nombre = i.name,
                            precio = i.price,
                            descripcion = i.description,
                            cat = i.category
                        )
                    }
                } else if (!filterC && !filterP) {
                    ProductItem(
                        viewModel = viewModel,
                        navController = navController,
                        nombre = i.name,
                        precio = i.price,
                        descripcion = i.description,
                        cat = i.category
                    )
                } else {
                    ProductItem(
                        viewModel = viewModel,
                        navController = navController,
                        nombre = i.name,
                        precio = i.price,
                        descripcion = i.description,
                        cat = i.category
                    )
                }
            }
        }
    }
}

@Composable
fun CheckBoxItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .selectable(selected = checked, onClick = { onCheckedChange(!checked) })
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
            }
}

@Composable
fun ProductItem(
    viewModel: catalogueViewModel,
    navController: NavHostController,
    nombre : String,
    precio: String,
    descripcion: String,
    cat : String
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {; navController.navigate("viewProduct")
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

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(android.graphics.Color.parseColor("#FFA8F5A6"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("initial_screen") }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }
            IconButton(onClick = { navController.navigate("product_management") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
            }
        }
    }
}

@Composable
fun adminBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(android.graphics.Color.parseColor("#FFA8F5A6"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("initial_screen") }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }

                IconButton(onClick = { /*navController.navigate("approved_product") */}) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Productos verificados")
                }

                IconButton(onClick = { /*navController.navigate("pending_product") */}) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "Productos pendientes")
                }


            IconButton(onClick = { navController.navigate("product_management") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
            }
        }
    }
}

// Este es el que tenemos implementado ya "BottomBar"
@Composable
fun sellerBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(android.graphics.Color.parseColor("#FFA8F5A6"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("initial_screen") }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }
            IconButton(onClick = { navController.navigate("product_management") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
            }
            IconButton(onClick = {/* navController.navigate("product_management") */}) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "CatalogueMain")
            }

            IconButton(onClick = { /*navController.navigate("product_management") */}) {
                Icon(imageVector = Icons.Default.Face, contentDescription = "CatalogueOwn")
            }
        }
    }
}

@Composable
fun clientBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(android.graphics.Color.parseColor("#FFA8F5A6"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("initial_screen") }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }

            IconButton(onClick = { /*navController.navigate("shopping_cart")*/ }) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
            }

            IconButton(onClick = { /*navController.navigate("wishing_list") */}) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Lista de deseos")
            }

            IconButton(onClick = { /*navController.navigate("gift_list") */}) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Listas de regalos")
            }

        }
    }
}