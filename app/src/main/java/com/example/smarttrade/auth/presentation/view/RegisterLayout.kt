package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smarttrade.R
import com.example.smarttrade.auth.presentation.viewmodel.RegisterViewModel
import com.example.smarttrade.ui.theme.Typography

/**
 * This is a Composable function that defines the layout for a registration screen.
 * It takes a Composable function as a parameter, which is used to inject a form into the layout.
 *
 * @param form A Composable function that defines the form to be displayed on the registration screen.
 */
@Composable
fun RegisterLayout(
    viewModel: RegisterViewModel,
    navController: NavController,
    content: @Composable () -> Unit
) {

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
            text = stringResource(id = R.string.register_title),
            style = Typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Inject the form into the layout
        content()

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.onRegister() }) {
            Text(text = "Registrarse")
        }
    }
}