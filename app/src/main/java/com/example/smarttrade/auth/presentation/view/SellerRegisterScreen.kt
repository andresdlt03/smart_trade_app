package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarttrade.auth.presentation.viewmodel.SellerRegisterViewModel

@Composable
fun SellerRegisterScreen(viewModel: SellerRegisterViewModel = hiltViewModel()) {
    RegisterLayout(viewModel) {
        SellerRegisterForm(viewModel)
    }
}

@Composable
fun SellerRegisterForm(viewModel: SellerRegisterViewModel = hiltViewModel()) {

    val state = viewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
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
            value = state.value.companyName,
            onValueChange = { viewModel.updateCompanyName(it) },
            placeholder = { Text("Nombre de la empresa") }
        )

        OutlinedTextField(
            value = state.value.cif,
            onValueChange = { viewModel.updateCif(it) },
            placeholder = { Text("CIF") }
        )
    }
}