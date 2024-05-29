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
                        HomeCatalogueScreen(navController = navController, scrollState = scrollState)
                    }
                }
            }
        }
    }
}