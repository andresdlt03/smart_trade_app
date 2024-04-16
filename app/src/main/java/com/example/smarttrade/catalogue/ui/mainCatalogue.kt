package com.example.smarttrade.catalogue.ui

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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.R

@Composable
fun mainCatalogueScreen(
    viewModel: mainCatalogueViewModel,
    navControler: NavHostController,
    scrollState: ScrollState
){
    Column {
        mainCatalogue(viewModel, navControler, scrollState)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainCatalogue(
    viewModel: mainCatalogueViewModel,
    navControler: NavHostController,
    scrollState: ScrollState
){
    val search :String by viewModel.search.observeAsState(initial = "")
    val filterCategory :Boolean by viewModel.filterCatgegory.observeAsState(initial = false)
    val filterPrice:Boolean by viewModel.filterPrice.observeAsState(initial = false)

    Scaffold (
        modifier = Modifier
            .background(color = Color.White
            ),
        containerColor = Color.White,
        bottomBar =  {
            BottomBar(navControler)
        }
    )

    {
        outLinedTextManage(
            search = search,
            filterCatgegory = filterCategory,
            filterPrice = filterPrice,
            activeFilterCategory = { viewModel.activeFilterCategory() },
            activeFilterPrice = { viewModel.activeFilterPrice() },
            UnActiveFilterCategory = { viewModel.unActiveFilterCategory() },
            UnActiveFilterPrice = { viewModel.unActiveFilterPrice() },
            viewModel = viewModel,
            navControler,
            scrollState = scrollState
        )
    }


}


@Composable
fun outLinedTextManage(
    search: String,
    filterCatgegory: Boolean,
    filterPrice: Boolean,
    activeFilterCategory:() -> Unit,
    activeFilterPrice:() -> Unit,
    UnActiveFilterCategory:() -> Unit,
    UnActiveFilterPrice:() -> Unit,
    viewModel: mainCatalogueViewModel,
    navControler: NavHostController,
    scrollState: ScrollState

){
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
        if(filterCatgegory == true) {

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
                        onClick = { UnActiveFilterCategory() },
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
                        onClick = { UnActiveFilterPrice()} ,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                    ) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { viewModel.disablePriceFilter();UnActiveFilterPrice()},
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
        Spacer(modifier = Modifier.height(20.dp))
        ProductoItem(viewModel, navControler )
        Spacer(modifier = Modifier.height(20.dp))
        ProductoItem2(viewModel, navControler )
        Spacer(modifier = Modifier.height(20.dp))
        ProductoItem3(viewModel, navControler )
        Spacer(modifier = Modifier.height(80.dp))


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
fun ProductoItem(
viewModel: mainCatalogueViewModel,
navControler: NavHostController
) {
    var nombre: String = "IPhone X"
    var imagenResId: Int = R.drawable.mobile_image
    var propiedad1: String = "800"
    var propiedad2: String = "Móvil de última generación de Apple"
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                viewModel.setProduct(
                    imagenResId,
                    nombre,
                    propiedad1,
                    propiedad2
                ); navControler.navigate("viewProduct")
            }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            modifier = Modifier.size(width = 80.dp, height = 80.dp),
            painter = painterResource(id = imagenResId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Precio: $propiedad1 €" , fontSize = 14.sp)
            Text(text = "Descripción: $propiedad2", fontSize = 14.sp)
        }
    }
}

@Composable
fun ProductoItem2(
    viewModel: mainCatalogueViewModel,
    navControler: NavHostController
) {
    var nombre: String = "El Hobbit libro"
    var imagenResId: Int = R.drawable.book_image
    var propiedad1: String = "20"
    var propiedad2: String = "Famoso libro de fantasía que narra las aventuras que han pasado el Hobbit y sus amigos."
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                viewModel.setProduct(
                    imagenResId,
                    nombre,
                    propiedad1,
                    propiedad2
                ); navControler.navigate("viewProduct")
            }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            modifier = Modifier.size(width = 80.dp, height = 80.dp),
            painter = painterResource(id = imagenResId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Precio: $propiedad1 €" , fontSize = 14.sp)
            Text(text = "Descripción: $propiedad2", fontSize = 14.sp)
        }
    }
}

@Composable
fun ProductoItem3(
    viewModel: mainCatalogueViewModel,
    navControler: NavHostController
) {
    var nombre: String = "Manzana"
    var imagenResId: Int = R.drawable.apple_image
    var propiedad1: String = "0.1"
    var propiedad2: String = "Es una manzana recogida a mano, de la marca AppleTree."
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                viewModel.setProduct(
                    imagenResId,
                    nombre,
                    propiedad1,
                    propiedad2
                ); navControler.navigate("viewProduct")
            }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            modifier = Modifier.size(width = 80.dp, height = 80.dp),
            painter = painterResource(id = imagenResId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Precio: $propiedad1 €" , fontSize = 14.sp)
            Text(text = "Descripción: $propiedad2", fontSize = 14.sp)
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
