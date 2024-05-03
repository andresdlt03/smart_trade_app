package com.example.smarttrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttrade.catalogue.ui.mainCatalogueScreen
import com.example.smarttrade.catalogue.ui.mainCatalogueViewModel
import com.example.smarttrade.catalogue.ui.viewProductCatalogueScreen
import com.example.smarttrade.catalogue.ui.viewProductCatalogueViewModel
import com.example.smarttrade.gift.presentation.giftScreen
import com.example.smarttrade.gift.presentation.giftViewModel
import com.example.smarttrade.auth.presentation.view.ClientRegisterScreen
import com.example.smarttrade.auth.presentation.view.LoginScreen
import com.example.smarttrade.auth.presentation.view.SellerRegisterScreen
import com.example.smarttrade.catalogue.view.mainCatalogueScreen
import com.example.smarttrade.catalogue.viewmodel.catalogueViewModel
import com.example.smarttrade.auth.presentation.viewmodel.LoginViewModel
import com.example.smarttrade.catalogue.view.viewProductCatalogueScreen
import com.example.smarttrade.catalogue.viewmodel.adminCatalogueViewModel
import com.example.smarttrade.catalogue.viewmodel.clientCatalogueViewModel
import com.example.smarttrade.catalogue.viewmodel.sellerCatalogueViewModel
import com.example.smarttrade.catalogue.viewmodel.viewProductCatalogueViewModel
import com.example.smarttrade.product_management.presentation.view.addProductBooksScreen
import com.example.smarttrade.product_management.presentation.view.addProductClothesScreen
import com.example.smarttrade.product_management.presentation.view.addProductFoodScreen
import com.example.smarttrade.product_management.presentation.view.addProductTechnologyScreen
import com.example.smarttrade.product_management.presentation.view.productManagementScreen
import com.example.smarttrade.ui.theme.SmartTradeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            SmartTradeTheme {
                val navController = rememberNavController()
                val scrollState = rememberScrollState()
                val viewmodel =
                    when (loginViewModel.getLoggedUserType()) {
                    "Client" -> clientCatalogueViewModel()
                    "Seller" -> sellerCatalogueViewModel()
                    "Admin" -> adminCatalogueViewModel()
                    else -> throw IllegalArgumentException("Tipo de usuario desconocido")
                }

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
                            scrollState = scrollState,
                            vm = sellerCatalogueViewModel()
                        )
                    }
                    composable("add2") {
                        addProductBooksScreen(
                            navHostController = navController,
                            scrollState = scrollState,
                            vm = sellerCatalogueViewModel()
                        )
                    }
                    composable("add3") {
                        addProductFoodScreen(
                            navHostController = navController,
                            scrollState = scrollState,
                            vm = sellerCatalogueViewModel()
                        )
                    }
                    composable("add4") {
                        addProductClothesScreen(
                            navHostController = navController,
                            scrollState = scrollState,
                            vm = sellerCatalogueViewModel()
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
                }
            }
        }
    }
}