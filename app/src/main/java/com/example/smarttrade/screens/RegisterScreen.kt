package com.example.smarttrade.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttrade.R
import com.example.smarttrade.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun ClientRegisterScreen() {

    val (name, onNameChange) = remember { mutableStateOf("") }
    val (surname, onSurnameChange) = remember { mutableStateOf("") }
    val (email, onEmailChange) = remember { mutableStateOf("") }
    val (password, onPasswordChange) = remember { mutableStateOf("") }

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = {
                        Text("Nombre")
                    }
                )
                OutlinedTextField(
                    value = surname,
                    onValueChange = onSurnameChange,
                    label = {
                        Text("Apellidos")
                    }
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = {
                        Text("Email")
                    }
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = {
                        Text("Contraseña")
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Registrarse")
            }
            
        }
    }
}