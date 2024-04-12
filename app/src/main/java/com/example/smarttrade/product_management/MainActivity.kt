package com.example.smarttrade.product_management

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttrade.product_management.ui.addProductTechnologyScreen
import com.example.smarttrade.product_management.ui.addProductTechnologyViewModel
import com.example.smarttrade.product_management.ui.productManagementScreen
import com.example.smarttrade.product_management.ui.productManagementViewModel

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val navControler = rememberNavController()
            NavHost(navController = navControler, startDestination = "main"){
                composable("main"){
                    productManagementScreen(viewModel = productManagementViewModel(), navControler)
                }
                composable("add1"){
                    addProductTechnologyScreen(viewModel = addProductTechnologyViewModel())
                }
            }
        }

    }
}


