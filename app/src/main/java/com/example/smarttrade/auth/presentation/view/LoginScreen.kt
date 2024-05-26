package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttrade.R
import com.example.smarttrade.auth.presentation.viewmodel.LoginViewModel
import com.example.smarttrade.components.OutlinedText
import com.example.smarttrade.ui.theme.Typography

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {

    val state = viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        LogoImage()
        Text(
            text = stringResource(id = R.string.login_title),
            style = Typography.titleLarge
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.onLogin() }) {
            Text(text = "Iniciar sesión")
        }

        state.value.loginError?.let {
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
        
        if(state.value.loginSuccess) {
            navController.navigate("catalogue")
        }
    }
}

