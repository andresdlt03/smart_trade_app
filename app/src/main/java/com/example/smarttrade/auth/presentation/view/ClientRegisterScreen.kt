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
import com.example.smarttrade.auth.presentation.viewmodel.ClientRegisterViewModel

@Composable
fun ClientRegisterScreen(
    viewModel: ClientRegisterViewModel = hiltViewModel(),
    navController: NavController
) {
    
    RegisterLayout(viewModel, navController) { 
        ClientRegisterForm(viewModel, navController)
    }
}

@Composable
fun ClientRegisterForm(
    viewModel: ClientRegisterViewModel = hiltViewModel(),
    navController: NavController
) {
    
    val state = viewModel.state.collectAsState()
    
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        OutlinedText(
            value = state.value.name,
            label = "Nombre",
            upDateField = { viewModel.updateName(it) },
            errorMessage = state.value.nameError
        )
        OutlinedText(
            value = state.value.surname,
            label = "Apellido *",
            upDateField = { viewModel.updateSurname(it) },
            errorMessage = state.value.surnameError
        )
        OutlinedText(
            value = state.value.email,
            label = "Email *",
            upDateField = { viewModel.updateEmail(it) },
            errorMessage = state.value.emailError
        )

        OutlinedText(
            value = state.value.password,
            label = "Contraseña *",
            upDateField = { viewModel.updatePassword(it) },
            errorMessage = state.value.passwordError
        )

        OutlinedText(
            value = state.value.dni,
            label = "DNI *",
            upDateField = { viewModel.updateDni(it) },
            errorMessage = state.value.dniError
        )

        OutlinedText(
            value = state.value.deliveryAddress,
            label = "Dirección de entrega *",
            upDateField = { viewModel.updateDeliveryAddress(it) },
            errorMessage = state.value.deliverDirError
        )

        OutlinedText(
            value = state.value.billingAddress,
            label = "Dirección de facturación",
            upDateField = { viewModel.updateBillingAddress(it) },
            errorMessage = state.value.factDirError
        )

        OutlinedText(
            value = state.value.creditCard,
            label = "Tarjeta de crédito",
            upDateField = { viewModel.updateCreditCard(it) },
            errorMessage = state.value.creditCardError
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
