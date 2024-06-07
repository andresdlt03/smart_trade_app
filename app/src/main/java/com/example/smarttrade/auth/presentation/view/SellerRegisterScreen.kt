package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttrade.NavRoutes
import com.example.smarttrade.auth.presentation.viewmodel.SellerRegisterViewModel
import com.example.smarttrade.components.OutlinedText

@Composable
fun SellerRegisterScreen(viewModel: SellerRegisterViewModel = hiltViewModel(),
                         navController: NavController) {
    RegisterLayout(viewModel, navController) {
        SellerRegisterForm(viewModel, navController)
    }
}

@Composable
fun SellerRegisterForm(viewModel: SellerRegisterViewModel = hiltViewModel(),
                       navController: NavController) {

    val state = viewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 16.dp)

    ) {
        OutlinedText(
            value = state.value.name,
            label = "Nombre",
            upDateField = { viewModel.updateName(it) },
            errorMessage = state.value.nameError
        )
        OutlinedText(
            value = state.value.surname,
            label = "Apellido",
            upDateField = { viewModel.updateSurname(it) },
            errorMessage = state.value.surnameError
        )
        OutlinedText(
            value = state.value.email,
            label = "Email",
            upDateField = { viewModel.updateEmail(it) },
            errorMessage = state.value.emailError
        )
        OutlinedText(
            value = state.value.password,
            label = "Contraseña",
            upDateField = { viewModel.updatePassword(it) },
            errorMessage = state.value.passwordError
        )
        OutlinedText(
            value = state.value.companyName,
            label = "Nombre de la empresa",
            upDateField = { viewModel.updateCompanyName(it) },
            errorMessage = state.value.companyNameError
        )
        OutlinedText(
            value = state.value.cif,
            label = "CIF",
            upDateField = { viewModel.updateCif(it) },
            errorMessage = state.value.cifError
        )
        OutlinedText(
            value = state.value.bankAccount,
            label = "IBAN",
            upDateField = { viewModel.updateBankAccount(it) },
            errorMessage = state.value.bankAccountError
        )

        state.value.registerError?.let {
            AlertDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = {
                    Button(onClick = { viewModel.clearError() }) {
                        Text(text = "Aceptar")
                    }
                },
                title = { Text("Error en el registro") },
                text = { Text(it) }
            )
        }

        if(state.value.registerSuccess) {
            AlertDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = {
                    Button(onClick = {
                        navController.navigate(NavRoutes.LOGIN.route)
                    }) {
                        Text(text = "Iniciar sesión")
                    }
                },
                text = { Text(text = "Usuario registrado con éxito") }
            )
        }
    }
}