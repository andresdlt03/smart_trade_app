package com.example.smarttrade.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.singleton.UserLogged

@Composable
fun BottomBar(
    navController: NavHostController
)
{
    val typeUser = UserLogged.userType;

    when (typeUser) {
        "admin" -> adminBottomBar(navController)
        "seller" -> sellerBottomBar(navController)
        "client" -> clientBottomBar(navController)
    }
}

@Composable
fun adminBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(android.graphics.Color.parseColor("#FFA8F5A6"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate(NavRoutes.HOME.route) }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }

            IconButton(onClick = { /*navController.navigate("approved_product") */}) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Productos verificados")
            }

            IconButton(onClick = { /*navController.navigate("pending_product") */}) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Productos pendientes")
            }

            IconButton(onClick = {
                navController.navigate(NavRoutes.INITIAL_SCREEN.route)
                UserLogged.logOut()
            }) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
            }
        }
    }
}

@Composable
fun sellerBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(android.graphics.Color.parseColor("#FFA8F5A6"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { navController.navigate(NavRoutes.HOME.route) }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }

            IconButton(onClick = { navController.navigate(NavRoutes.ADD_PRODUCT.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar producto")
            }

            IconButton(onClick = {
                navController.navigate(NavRoutes.INITIAL_SCREEN.route)
                UserLogged.logOut()
            }) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
            }
        }
    }
}

@Composable
fun clientBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(android.graphics.Color.parseColor("#FFA8F5A6"))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate(NavRoutes.HOME.route) }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
            }

            IconButton(onClick = { navController.navigate(NavRoutes.SHOPPING_CART.route) }) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Carrito")
            }

            IconButton(onClick = { /*navController.navigate("wishingList")*/ }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Lista de deseos")
            }

            IconButton(onClick = { /*navController.navigate("gift_list") */}) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Listas de regalos")
            }
            IconButton(onClick = {
                navController.navigate(NavRoutes.INITIAL_SCREEN.route)
                UserLogged.logOut()
            }) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
            }
        }
    }
}