package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttrade.auth.presentation.viewmodel.ClientRegisterViewModel
import com.example.smarttrade.auth.presentation.viewmodel.state.ClientRegisterState
import com.example.smarttrade.ui.theme.md_theme_light_error

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
        outlinedText(value = state.value.name, label = "Nombre *", {viewModel.updateName(it)})
        state.value.nameError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.surname, label = "Apellido *", {viewModel.updateSurname(it)})
        state.value.surnameError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.email, label = "Email *", {viewModel.updateEmail(it)})
        state.value.emailError?.let{ ErrorText(text = it)}
        outlinedText(value = state.value.password, label = "Contraseña *", {viewModel.updatePassword(it)})
        state.value.passwordError?.let { ErrorText(text = it) }
        outlinedText(value = state.value.dni,  label = "DNI *", {viewModel.updateDni(it)})
        state.value.dniError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.deliverDir,  label = "Dirección de entrega *", {viewModel.updateDeliverDir(it)})
        state.value.deliverDirError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.factDir, label = "Dirección de facturación", {viewModel.updatefactDire(it)})
        state.value.factDirError?.let { ErrorText(text = it)}
        outlinedText(value = state.value.creditCard, label = "Tarjeta de credito", {viewModel.updateCreditCard(it)})
        state.value.creditCardError?.let { ErrorText(text = it)}

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
