package com.example.smarttrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttrade.catalogue.ui.mainCatalogueScreen
import com.example.smarttrade.catalogue.ui.mainCatalogueViewModel
import com.example.smarttrade.catalogue.ui.viewProductCatalogueScreen
import com.example.smarttrade.catalogue.ui.viewProductCatalogueViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewmodel =  mainCatalogueViewModel()
            val navControler = rememberNavController()
            NavHost(navController = navControler, startDestination = "catalogue") {
                composable("catalogue") {
                    mainCatalogueScreen(viewmodel, navControler)
                }
                composable("viewProduct") {
                    viewProductCatalogueScreen(
                        viewProductCatalogueViewModel(),
                        navControler,
                        viewmodel
                    )
                }
            }
        }
    }
}

