package com.example.smarttrade.catalogue.ui

import android.widget.ToggleButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@Composable
fun mainCatalogueScreen(viewModel: mainCatalogueViewModel){
    Column {
        mainCatalogue(viewModel)
    }
}

@Composable
fun mainCatalogue(viewModel: mainCatalogueViewModel){
    val search :String by viewModel.search.observeAsState(initial = "")
    val filterCategory :Boolean by viewModel.filterCatgegory.observeAsState(initial = false)
    val filterPrice:Boolean by viewModel.filterPrice.observeAsState(initial = false)

    outLinedTextManage(
        search = search,
        filterCatgegory = filterCategory,
        filterPrice = filterPrice,
        activeFilterCategory = {viewModel.activeFilterCategory()},
        activeFilterPrice = {viewModel.activeFilterPrice()},
        UnActiveFilterCategory = {viewModel.unActiveFilterCategory()},
        UnActiveFilterPrice = {viewModel.unActiveFilterPrice()},
        viewModel = viewModel
    )


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
    viewModel: mainCatalogueViewModel

){
    Column() {
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
                    modifier = Modifier.clickable {}

                )
            },
            value = search,
            label = { Text("Buscar un producto") },
            onValueChange = {}
        )
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
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
                onDismissRequest = { UnActiveFilterCategory() },
                title = { Text("Filtrar por categoría") },
                confirmButton = {
                    Row(
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = { UnActiveFilterCategory() }) {
                            Text("Aceptar")
                        }
                    }
                },
                dismissButton = {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Button(onClick = {
                            UnActiveFilterCategory()
                            viewModel.cancelFilterCategory()
                        }) {
                            Text("Cancelar")
                        }
                    }
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
            val maxPrice by viewModel.maxPrice.observeAsState(100F)

            AlertDialog(

                onDismissRequest = { UnActiveFilterPrice() },
                title = { Text("Filtrar por precio") },
                confirmButton = {
                    Button(onClick = { UnActiveFilterPrice() }) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    Button(onClick = { viewModel.disablePriceFilter();UnActiveFilterPrice() }) {
                        Text("Cancelar")
                    }
                },
                text ={
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        minPrice?.let { currentMinPrice ->
                            Text("Precio mínimo: $currentMinPrice")
                            Slider(
                                value = currentMinPrice,
                                onValueChange = { viewModel.setMinPrice(it) },
                                valueRange = viewModel.priceRange,
                                steps = 100,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        maxPrice?.let { currentMaxPrice ->
                            Text("Precio máximo: $currentMaxPrice")
                            Slider(
                                value = currentMaxPrice,
                                onValueChange = { viewModel.setMaxPrice(it) },
                                valueRange = viewModel.priceRange,
                                steps = 100,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                }

            )
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

