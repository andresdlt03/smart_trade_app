package com.example.smarttrade.product_management.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttrade.R


@Composable
fun addProductTechnologyScreen(viewModel: addProductTechnologyViewModel) {

    var (name) = remember { mutableStateOf("") }
    var (description) = remember { mutableStateOf("") }
    var (model) = remember { mutableStateOf("") }
    var (energy) = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        addProductTechnology(viewModel)
    }
}

@Composable
fun addProductTechnology(viewModel: addProductTechnologyViewModel){

    val name :String by viewModel.name.observeAsState(initial = "Nombre (20 carácteres)")
    val description :String by viewModel.description.observeAsState(initial = "Descripción (50 carácteres)")
    val model :String by viewModel.model.observeAsState(initial = "Modelo")
    val energy :String by viewModel.energy.observeAsState(initial = "Consumo energético (kWh)")

    topBarAdd()
    Spacer(modifier = Modifier.height(24.dp))
    outLinedTextAdd(name, {text("Nombre")}, 1,{viewModel.onItemChanged(it,1)} ,{ viewModel.clearSelected(1) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(description, {text("Descripción")}, 2,{viewModel.onItemChanged(it,2)} ,{ viewModel.clearSelected(2) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(model, {text("Modelo")}, 3,{viewModel.onItemChanged(it,3)} ,{ viewModel.clearSelected(3) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(model, {text("Consumo energético")}, 4,{viewModel.onItemChanged(it,4)} ,{ viewModel.clearSelected(4) })
    Spacer(modifier = Modifier.height(54.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = { },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_foto),
                    contentDescription = null
                )
                }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Añade una foto del producto")
        }
    }
    Spacer(modifier = Modifier.height(54.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { })
        {
            Text(text = "Publicar producto")
        }
    }
}

@Composable
fun topBarAdd(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { },
        ) {
            Icon(Icons.Filled.ArrowBack, "Back button")
        }
        Spacer(modifier = Modifier.width(34.dp))
        Text(
            text = "Datos del producto",
            fontSize = 22.sp
        )
    }
}

@Composable
fun text(text: String){
    Text(text = text)
}


@Composable
fun outLinedTextAdd(item: String, text: @Composable (String) -> Unit, id: Int, onItemChanged:(String) -> Unit, clearSelected:(Int) -> Unit){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .clickable {
                        clearSelected(id)
                    },
            )
        },
        value = item,
        label = { text },
        onValueChange = {onItemChanged(it)}
    )
}


