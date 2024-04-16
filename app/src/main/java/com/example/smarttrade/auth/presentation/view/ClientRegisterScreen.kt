package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.smarttrade.auth.presentation.viewmodel.ClientRegisterViewModel
import com.example.smarttrade.ui.theme.md_theme_light_error

@Composable
fun ClientRegisterScreen(viewModel: ClientRegisterViewModel = hiltViewModel(),
                         navController: NavController) {
    RegisterLayout(viewModel, navController) {
        ClientRegisterForm(viewModel, navController)
    }
}

@Composable
fun ClientRegisterForm(viewModel: ClientRegisterViewModel = hiltViewModel(),
                       navController: NavController) {

    val state = viewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.name,
            onValueChange = { viewModel.updateName(it) },
            placeholder = { Text("Nombre") }
        )
        state.value.nameError?.let {
            Text(
                text = it,
                color = md_theme_light_error
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.surname,
            onValueChange = { viewModel.updateSurname(it) },
            placeholder = { Text("Apellido") }
        )
        state.value.surnameError?.let {
            Text(
                text = it,
                color = md_theme_light_error
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.email,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = { Text("Email") }
        )
        state.value.emailError?.let {
            Text(
                text = it,
                color = md_theme_light_error
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.password,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = { Text("Contraseña") }
        )
        state.value.passwordError?.let {
            Text(
                text = it,
                color = md_theme_light_error
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.value.dni,
            onValueChange = { viewModel.updateDni(it) },
            placeholder = { Text("DNI") }
        )
        state.value.dniError?.let {
            Text(
                text = it,
                color = md_theme_light_error
            )
        }

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