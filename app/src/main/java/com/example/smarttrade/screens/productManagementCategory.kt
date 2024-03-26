package com.example.smarttrade.screens

import android.graphics.drawable.Icon
import android.widget.Button
import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttrade.R
import androidx.compose.ui.Modifier

@Preview
@Composable
fun App() {

    val (name, setName) = remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp), // Usar la función withPadding para agregar padding
        verticalArrangement = Arrangement.Top,
    ) {
        Row {
            FloatingActionButton(
                onClick = { },
            ) {
                Icon(Icons.Filled.ArrowBack, "Floating action button.")
            }
            Spacer(modifier = Modifier.width(14.dp))
            Text("Selecciona una categoría")


        }
        val (name, setName) = remember { mutableStateOf("") }
        OutlinedTextField(
            value = name ,
            onValueChange = setName,
            leadingIcon = Icons.Filled.Search

        )
    }
}




