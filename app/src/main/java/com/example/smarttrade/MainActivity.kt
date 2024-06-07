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
import com.example.smarttrade.catalogue.presentation.view.HomeCatalogueScreen
import com.example.smarttrade.lists.view.CartScreen
import com.example.smarttrade.product_management.presentation.view.ProductManagementScreen
import com.example.smarttrade.catalogue.view.OrderFinishedScreen
import com.example.smarttrade.catalogue.view.ProcessOrder
import com.example.smarttrade.gift.presentation.giftScreen
import com.example.smarttrade.gift.presentation.giftViewModel
import com.example.smarttrade.product_management.presentation.view.addProductBooksScreen
import com.example.smarttrade.product_management.presentation.view.addProductClothesScreen
import com.example.smarttrade.product_management.presentation.view.addProductFoodScreen
import com.example.smarttrade.product_management.presentation.view.addProductTechnologyScreen
import com.example.smarttrade.ui.theme.SmartTradeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SmartTradeTheme {
                val navController = rememberNavController()
                val scrollState = rememberScrollState()

                NavHost(navController = navController, startDestination = "initial_screen") {
                    composable(NavRoutes.INITIAL_SCREEN.route) {
                        InitialScreen(
                            navController = navController
                        )
                    }
                    composable(NavRoutes.LOGIN.route) {
                        LoginScreen(
                            navController = navController
                        )
                    }
                    composable(NavRoutes.REGISTER_CLIENT.route) {
                        ClientRegisterScreen(
                            navController = navController
                        )
                    }
                    composable(NavRoutes.REGISTER_SELLER.route) {
                        SellerRegisterScreen(
                            navController = navController
                        )
                    }
                    composable(NavRoutes.HOME.route) {
                        HomeCatalogueScreen(
                            navController = navController,
                            scrollState = scrollState
                        )
                    }
                    composable(NavRoutes.ADD_PRODUCT.route) {
                        ProductManagementScreen(
                            navController = navController
                        )
                    }
                    composable(NavRoutes.ADD_TECHNOLOGY.route) {
                        addProductTechnologyScreen(
                            navHostController = navController,
                            scrollState = scrollState
                        )
                    }
                    composable(NavRoutes.ADD_BOOKS.route) {
                        addProductBooksScreen(
                            navHostController = navController,
                            scrollState = scrollState
                        )
                    }
                    composable(NavRoutes.ADD_FOOD.route) {
                        addProductFoodScreen(
                            navHostController = navController,
                            scrollState = scrollState
                        )
                    }
                    composable(NavRoutes.ADD_CLOTHES.route) {
                        addProductClothesScreen(
                            navHostController = navController,
                            scrollState = scrollState
                        )
                    }
                    composable(NavRoutes.SHOPPING_CART.route) {
                        CartScreen(
                            navController = navController
                        )
                    }
                    composable(NavRoutes.GIFT_LIST.route){
                        giftScreen(giftViewModel(),navController)
                    }
                    composable(NavRoutes.PAYMENT.route){
                        ProcessOrder(navController, scrollState)
                    }
                    composable(NavRoutes.FINISH_ORDER.route){
                        OrderFinishedScreen(navController)
                    }
                }
            }
        }
    }
}