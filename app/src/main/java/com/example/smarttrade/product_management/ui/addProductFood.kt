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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttrade.R

@Preview
@Composable
fun AppFood() {

    var (name) = remember { mutableStateOf("") }
    var (description) = remember { mutableStateOf("") }
    var (calories) = remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
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
        Spacer(modifier = Modifier.height(24.dp))
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
                            name = ""
                        },
                )
            },
            value = "Nombre (20 carácteres)",
            label = { Text("Nombre") },
            onValueChange = {name = it}
        )
        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),

                trailingIcon = {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 18.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                description = ""
                            }
                        )
                    }
                },
            value = "Descripción (50 carácteres)",
            label = { Text("Descripción") },
            onValueChange = {description = it}
        )
        Spacer(modifier = Modifier.height(14.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable{
                        calories = ""
                    }
                )
            },
            value = "Calorias",
            label = { Text("Calorias") },
            onValueChange = {calories = it}
        ) 
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
}



