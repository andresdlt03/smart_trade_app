package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttrade.auth.presentation.viewmodel.SellerRegisterViewModel
import com.example.smarttrade.ui.theme.md_theme_light_error

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
        outlinedText(value = state.value.name, label = "Nombre") { viewModel.updateName(it)  }
        state.value.nameError?.let {ErrorText(text = it)}
        outlinedText(value = state.value.surname, label = "Apellido") {viewModel.updateSurname(it)}
        state.value.surnameError?.let {ErrorText(text = it)}
        outlinedText(value = state.value.email, label = "Email") {viewModel.updateEmail(it)}
        state.value.emailError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.password, label = "Contraseña") { viewModel.updatePassword(it)}
        state.value.passwordError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.companyName, label ="Nombre de la empresa" ) {viewModel.updateCompanyName(it)}
        state.value.companyNameError?.let {ErrorText(text = it)}
        outlinedText(value = state.value.cif, label = "CIF") {viewModel.updateCif(it)}
        state.value.cifError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.bankAccount, label ="IBAN" ) {viewModel.updateBankAccount(it)}
        state.value.bankAccountError?.let {ErrorText(text = it)}
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
                        navController.navigate("login")
                    }) {
                        Text(text = "Iniciar sesión")
                    }
                },
                text = { Text(text = "Usuario registrado con éxito") }
            )
        }
    }
}