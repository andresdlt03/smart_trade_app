package com.example.smarttrade.screens

import android.graphics.drawable.Icon
import android.widget.Button
import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttrade.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun AppCategory() {

    val categories = listOf(
        Category("Tecnología",Icons.Filled.Build),
        Category( "Libros",Icons.Filled.Email),
        Category("Comida",Icons.Filled.Home),
        Category("Ropa",Icons.Filled.Face)
    )
    var (text) = remember { mutableStateOf("") }

    val filteredCategories = categories.filter {
        it.name.contains(text, ignoreCase = true)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            FloatingActionButton(
                onClick = { },
            ) {
                Icon(Icons.Filled.ArrowBack, "Back button")
            }
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "Selecciona una categoría",
                fontSize = 22.sp
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
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
                    contentDescription = null
                )
            },
            value = "Buscar una categoría",
            label = { Text("Categoría") },
            onValueChange = {text = it}
        )
        Spacer(modifier = Modifier.height(14.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        )
        {
            for ( category in filteredCategories)
                ListItem(category = category)
        }
    }
}

@Composable
fun ListItem(category: Category){
    ListItem(
        headlineContent = { Text(text = category.name) },
        leadingContent = {
            Icon(
                category.icon,
                contentDescription = category.name
            )
        }
    )
}

data class Category(
    val name: String,
    val icon: ImageVector
)

