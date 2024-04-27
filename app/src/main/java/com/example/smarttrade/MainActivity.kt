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
import com.example.smarttrade.catalogue.ui.mainCatalogueScreen
import com.example.smarttrade.catalogue.ui.mainCatalogueViewModel
import com.example.smarttrade.catalogue.ui.viewProductCatalogueScreen
import com.example.smarttrade.catalogue.ui.viewProductCatalogueViewModel
import com.example.smarttrade.gift.presentation.giftScreen
import com.example.smarttrade.gift.presentation.giftViewModel
import com.example.smarttrade.product_management.presentation.view.addProductBooksScreen
import com.example.smarttrade.product_management.presentation.view.addProductClothesScreen
import com.example.smarttrade.product_management.presentation.view.addProductFoodScreen
import com.example.smarttrade.product_management.presentation.view.addProductTechnologyScreen
import com.example.smarttrade.product_management.presentation.view.productManagementScreen
import com.example.smarttrade.ui.theme.SmartTradeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel = mainCatalogueViewModel()
            SmartTradeTheme {
                val navController = rememberNavController()
                val scrollState = rememberScrollState()

                NavHost(navController = navController, startDestination = "gift") {
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
                            scrollState = scrollState,
                            vm = viewmodel
                        )
                    }
                    composable("add2") {
                        addProductBooksScreen(
                            navHostController = navController,
                            scrollState = scrollState,
                            vm = viewmodel
                        )
                    }
                    composable("add3") {
                        addProductFoodScreen(
                            navHostController = navController,
                            scrollState = scrollState,
                            vm = viewmodel
                        )
                    }
                    composable("add4") {
                        addProductClothesScreen(
                            navHostController = navController,
                            scrollState = scrollState,
                            vm = viewmodel
                        )
                    }
                    composable("catalogue") {
                        mainCatalogueScreen(viewmodel, navController,scrollState)
                    }
                    composable("viewProduct") {
                        viewProductCatalogueScreen(
                            viewProductCatalogueViewModel(),
                            navController,
                            viewmodel
                        )
                    }
                    composable("gift"){
                        giftScreen(navController =navController , viewModel = giftViewModel())
                    }
                }
            }
        }
    }
}