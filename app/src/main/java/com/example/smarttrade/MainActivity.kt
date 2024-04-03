package com.example.smarttrade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smarttrade.auth.presentation.view.ClientRegisterScreen
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
                        .padding(vertical = 48.dp)
                ) {
                    ClientRegisterScreen()
                }
            }
        }
    }
}
