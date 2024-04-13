package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarttrade.R
import com.example.smarttrade.auth.presentation.viewmodel.LoginViewModel
import com.example.smarttrade.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
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

        Spacer(modifier = Modifier.height(16.dp))



        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.onLogin() }) {
            Text(text = "Registrarse")
        }
    }

}