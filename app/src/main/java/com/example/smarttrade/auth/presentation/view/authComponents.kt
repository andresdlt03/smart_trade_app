package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.smarttrade.R
import com.example.smarttrade.auth.presentation.viewmodel.ClientRegisterViewModel
import com.example.smarttrade.ui.theme.md_theme_light_error


@Composable
fun outlinedText(value:String, label:String, upDateField:(String) -> Unit){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {upDateField },
        label = { Text(text = label) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .clickable {
                        {}
                    },
            )
        }
    )
}

@Composable
fun ErrorText(text:String){
    Text(
        text = text,
        color = md_theme_light_error
    )
}

@Composable
fun LogoImage(){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.logo_description),
        modifier = Modifier.size(100.dp)
    )
}