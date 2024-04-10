package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarttrade.auth.presentation.viewmodel.ClientRegisterViewModel

@Preview
@Composable
fun ClientRegisterScreen(viewModel: ClientRegisterViewModel = hiltViewModel()) {
    RegisterLayout(viewModel) {
        ClientRegisterForm(viewModel)
    }
}

@Composable
fun ClientRegisterForm(viewModel: ClientRegisterViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        OutlinedTextField(
            value = state.value.name,
            onValueChange = { viewModel.updateName(it) },
            placeholder = { Text("Nombre") }
        )
        OutlinedTextField(
            value = state.value.surname,
            onValueChange = { viewModel.updateSurname(it) },
            placeholder = { Text("Apellido") }
        )
        OutlinedTextField(
            value = state.value.email,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = { Text("Email") }
        )
        OutlinedTextField(
            value = state.value.password,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = { Text("Contraseña") }
        )
        OutlinedTextField(
            value = state.value.dni,
            onValueChange = { viewModel.updateDni(it) },
            placeholder = { Text("DNI") }
        )
    }
}