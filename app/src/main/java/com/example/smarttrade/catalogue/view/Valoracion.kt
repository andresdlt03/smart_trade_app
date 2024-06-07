package com.example.smarttrade.catalogue.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.smarttrade.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingScreen() {
    var rating by remember { mutableStateOf(0) }
    var opinion by remember { mutableStateOf(TextFieldValue("")) }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Valoración") },
                navigationIcon = {
                    IconButton(onClick = { /* VOLVER AL CATALOGO */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.apple_image),
                    contentDescription = "Camiseta básica",
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Camiseta básica", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                RatingBar(rating = rating) { newRating ->
                    rating = newRating
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = opinion,
                    onValueChange = { opinion = it },
                    label = { Text("Déjanos tu opinión!") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (opinion.text.isNotBlank()) {
                        ValoracionesRepository.addValoracion(
                            Valoracion(estrellas = rating, opinion = opinion.text)
                        )
                        /*VOLVER A LA PESTAÑA ANTERIOR*/
                    } else {
                        showError = true
                    }
                }) {
                    Text("Enviar valoración")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (showError) {
                    Text(
                        text = "Por favor, añade un comentario.",
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun RatingBar(rating: Int, onRatingChanged: (Int) -> Unit) {
    Row {
        for (i in 1..5) {
            Icon(
                imageVector  =if (i <= rating) Icons.Filled.Star else Icons.Filled.FavoriteBorder,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingScreenPreview() {
    RatingScreen()
}
