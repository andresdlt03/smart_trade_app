package com.example.smarttrade.gift.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import  com.example.smarttrade.gift.presentation.giftViewModel


@Composable
fun giftScreen(viewModel: giftViewModel ,
               navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        gift(viewModel, navController)
    }
}

@Composable
fun gift(viewModel: giftViewModel, navController: NavHostController){

    val activePop :Boolean by viewModel.activatePop.observeAsState(initial = false)

    topBarAdd(goBackCategories = {viewModel.goBackCategories(navController)}, navController = navController)
    Spacer(modifier = Modifier.height(15.dp))
    listToGift(viewModel)
    if(activePop){MyPopupWindow(viewModel) { viewModel.unActivePop() }
    }
}


@Composable
fun topBarAdd(goBackCategories:(NavHostController) -> Unit , navController: NavHostController){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { goBackCategories(navController)},
        ) {
            Icon(Icons.Filled.ArrowBack, "Back button")
        }
        Spacer(modifier = Modifier.width(34.dp))
        Text(
            text = "Regalo",
            fontSize = 22.sp
        )
    }
}

@Composable
fun listToGift(viewModel: giftViewModel){
    Column {
        Text(text = "¿En qué lista quiere guardar este regalo?")
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            boton1(viewModel)
            Spacer(modifier = Modifier.width(15.dp))
            boton2(viewModel)
        }
    }
}

@Composable
fun boton1(viewModel: giftViewModel){

    val selectedList :String by viewModel.selectedList.observeAsState(initial =
    viewModel.getFirstofList())

    Button(
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
        border = BorderStroke(0.4.dp, color = Color.Black),
        onClick = { /*TODO*/ }) {
        Text(text = selectedList)
    }
}

@Composable

fun boton2(viewModel: giftViewModel){
    Button(
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
        border = BorderStroke(0.4.dp, color = Color.Black),
        onClick = { viewModel.activePop() }) {
        Text(text = "Cambiar")
    }
}

@Composable
fun MyPopupWindow(viewModel: giftViewModel, onDismiss: () -> Unit) {
    val newString = remember { mutableStateOf("") }
    val lists :List<String> by viewModel.lists.observeAsState(initial =
    listOf())

    Dialog(
        onDismissRequest = {viewModel.unActivePop()},

    ) {

        Column(
            modifier = Modifier
                .padding(26.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(15))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Listas de Regalo", fontSize = 25.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Selecciona o crea una lista nueva", fontSize = 15.sp)
            Spacer(modifier = Modifier.height(12.dp))

            for (i in lists){
                Text(
                    text = i,
                    modifier = Modifier
                        .clickable { viewModel.changeSelectedList(i);viewModel.unActivePop() }
                        .background(color = Color.LightGray, shape = RoundedCornerShape(15))
                        .padding(5.dp),

                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = newString.value,
                onValueChange = { newString.value = it },
                label = { Text("Crea una lista de regalo") },
                modifier = Modifier.padding(9.dp)

            )

            Spacer(modifier = Modifier.height(9.dp))

            Button(
                onClick = {
                    if(newString.value.trim() != "") {
                    viewModel.addStringToList(newString.value)
                    newString.value = ""}
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Añadir")
            }
        }
    }
}

