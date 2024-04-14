package com.example.smarttrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.smarttrade.auth.presentation.view.SellerRegisterScreen
import com.example.smarttrade.ui.theme.SmartTradeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartTradeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    SellerRegisterScreen()
                }
            }
        }
    }
}
