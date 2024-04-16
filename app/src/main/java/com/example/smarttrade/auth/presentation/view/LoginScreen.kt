package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttrade.R
import com.example.smarttrade.auth.presentation.viewmodel.LoginViewModel
import com.example.smarttrade.ui.theme.Typography
import com.example.smarttrade.ui.theme.md_theme_light_error

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {

    val state = viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo_description),
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(id = R.string.login_title),
            style = Typography.titleLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        state.value.errorMessage?.let {
            Text(
                text = it,
                color = md_theme_light_error
            )
        }

        Button(onClick = { viewModel.onLogin(); navController.navigate("catalogue") }) {
            Text(text = "Iniciar sesión")
        }
    }

}