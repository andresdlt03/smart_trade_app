package com.example.smarttrade.ratings

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.view.Rating
import com.example.smarttrade.catalogue.view.RatingsState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingScreen(
    navHostController: NavHostController
) {
    var rating by remember { mutableStateOf(0) }
    var opinion by remember { mutableStateOf(TextFieldValue("")) }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Valoración",
                    fontSize = 20.sp
                ) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigate(NavRoutes.ORDERS_HISTORY.route) }) {
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
                    .padding(16.dp)
                    .padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.camiseta),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                RatingBar(rating = rating) { newRating ->
                    rating = newRating
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = opinion,
                    onValueChange = { opinion = it },
                    label = {
                        Text(
                            text = "Déjanos tu opinión!",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                            },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(4.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (opinion.text.isNotBlank()) {
                        RatingsState.addValoracion(
                            Rating(stars = rating, opinion = opinion.text)
                        )
                        navHostController.popBackStack()
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
                tint = Color.Black,
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}