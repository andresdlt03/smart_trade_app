package com.example.smarttrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttrade.product_management.presentation.view.addProductBooksScreen
import com.example.smarttrade.product_management.presentation.view.addProductClothesScreen
import com.example.smarttrade.product_management.presentation.view.addProductFoodScreen
import com.example.smarttrade.product_management.presentation.view.addProductTechnologyScreen
import com.example.smarttrade.product_management.presentation.view.productManagementScreen
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductBookViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductClothesViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductFoodViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductTechnologyViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.ProductManagementViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scrollState = rememberScrollState()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    productManagementScreen(
                        viewModel = ProductManagementViewModel(),
                        navController
                    )
                }
                composable("add1") {
                    addProductTechnologyScreen(
                        viewModel = AddProductTechnologyViewModel(),
                        navController,
                        scrollState
                    )
                }
                composable("add2") {
                    addProductBooksScreen(
                        viewModel = AddProductBookViewModel(),
                        navController,
                        scrollState
                    )
                }
                composable("add3") {
                    addProductFoodScreen(
                        viewModel = AddProductFoodViewModel(),
                        navController,
                        scrollState
                    )
                }
                composable("add4") {
                    addProductClothesScreen(
                        viewModel = AddProductClothesViewModel(),
                        navController,
                        scrollState
                    )
                }
            }
        }
    }
}
