package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarttrade.auth.presentation.viewmodel.ClientRegisterViewModel

@Composable
fun ClientRegisterScreen(viewModel: ClientRegisterViewModel = hiltViewModel()) {
    RegisterLayout(viewModel) {
        ClientRegisterForm(viewModel)
    }
}

@Composable
fun ClientRegisterForm(viewModel: ClientRegisterViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()

    Column {
        Text(text = "Users: ${state.value.users}")
    }
}