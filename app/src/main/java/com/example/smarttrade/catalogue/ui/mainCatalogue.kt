package com.example.smarttrade.catalogue.ui

import android.widget.ToggleButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@Composable
fun mainCatalogueScreen(viewModel: productManagementViewModel){
    Column {
        mainCatalogue(viewModel)
    }
}

@Composable
fun mainCatalogue(viewModel: productManagementViewModel){
    val search :String by viewModel.search.observeAsState(initial = "")
    val selectedCategories: List<String> by viewModel.selectedCategories.observeAsState(emptyList())
    val filterCategory :Boolean by viewModel.filterCatgegory.observeAsState(initial = false)
    val filterPrice:Boolean by viewModel.filterPrice.observeAsState(initial = false)
}


@Composable
fun outLinedTextManage(
    search: String,
    selectedCategories: MutableList<String>,
    filterCatgegory: Boolean,
    filterPrice: Boolean,
    activeFilterCategory:() -> Unit,
    activeFilterPrice:() -> Unit


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
        if(filterCatgegory == true){
            AlertDialog(
                onDismissRequest = {  },
                title = { Text("Filtrar por categoría") },
                confirmButton = {
                    Button(onClick = {  }) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    Button(onClick = {  }) {
                        Text("Cancelar")
                    }
                },
                text = {
                    Column {
                        CategoryCheckBox("Tecnología", selectedCategories)
                        CategoryCheckBox("Libros", selectedCategories)
                        CategoryCheckBox("Ropa", selectedCategories)
                        CategoryCheckBox("Comida", selectedCategories)
                    }
                }
            )
        }

        if(filterPrice == true){
            AlertDialog(
                onDismissRequest = {  },
                title = { Text("Filtrar por precio") },
                confirmButton = {
                    Button(onClick = {  }) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    Button(onClick = {  }) {
                        Text("Cancelar")
                    }
                },
                // Aquí puedes incluir opciones para filtrar por precio
                text = { Text("Opciones de filtrado de precio") }
            )
        }


    }
}


@Composable
fun PriceFilterDialog(
) {
    AlertDialog(
        onDismissRequest = {  },
        title = { Text("Filtrar por precio") },
        confirmButton = {
            Button(onClick = {  }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = {  }) {
                Text("Cancelar")
            }
        },
        // Aquí puedes incluir opciones para filtrar por precio
        text = { Text("Opciones de filtrado de precio") }
    )
}


@Composable
fun CategoryFilterDialog(selectedCategories: MutableList<String>
) {

    AlertDialog(
        onDismissRequest = {  },
        title = { Text("Filtrar por categoría") },
        confirmButton = {
            Button(onClick = {  }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = {  }) {
                Text("Cancelar")
            }
        },
        text = {
            Column {
                CategoryCheckBox("Tecnología", selectedCategories)
                CategoryCheckBox("Libros", selectedCategories)
                CategoryCheckBox("Ropa", selectedCategories)
                CategoryCheckBox("Comida", selectedCategories)
            }
        }
    )
}

@Composable
fun CategoryCheckBox(
    category: String,
    selectedCategories: MutableList<String>
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = selectedCategories.contains(category),
            onCheckedChange = { isChecked ->
                if (isChecked) {
                    selectedCategories.add(category)
                } else {
                    selectedCategories.remove(category)
                }
            }
        )
        Text(text = category)
    }
}
