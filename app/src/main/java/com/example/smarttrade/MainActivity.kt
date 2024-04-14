package com.example.smarttrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttrade.auth.presentation.view.ClientRegisterScreen
import com.example.smarttrade.auth.presentation.view.LoginScreen
import com.example.smarttrade.auth.presentation.view.SellerRegisterScreen
import com.example.smarttrade.product_management.presentation.view.addProductBooksScreen
import com.example.smarttrade.product_management.presentation.view.addProductClothesScreen
import com.example.smarttrade.product_management.presentation.view.addProductFoodScreen
import com.example.smarttrade.product_management.presentation.view.addProductTechnologyScreen
import com.example.smarttrade.product_management.presentation.view.productManagementScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scrollState = rememberScrollState()
            NavHost(navController = navController, startDestination = "initial_screen") {
                composable("initial_screen") {
                    InitialScreen(
                        navController = navController
                    )
                }
                composable("login") {
                    LoginScreen(
                        navController = navController
                    )
                }
                composable("register_client") {
                    ClientRegisterScreen(
                        navController = navController
                    )
                }
                composable("register_seller") {
                    SellerRegisterScreen(
                        navController = navController
                    )
                }
                composable("product_management") {
                    productManagementScreen(
                        navController = navController
                    )
                }
                composable("add1") {
                    addProductTechnologyScreen(
                        navHostController = navController,
                        scrollState = scrollState
                    )
                }
                composable("add2") {
                    addProductBooksScreen(
                        navHostController = navController,
                        scrollState = scrollState
                    )
                }
                composable("add3") {
                    addProductFoodScreen(
                        navHostController = navController,
                        scrollState = scrollState
                    )
                }
                composable("add4") {
                    addProductClothesScreen(
                        navHostController = navController,
                        scrollState = scrollState
                    )
                }
            }
        }
    }
}
