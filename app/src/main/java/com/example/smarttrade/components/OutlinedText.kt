package com.example.smarttrade.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smarttrade.ui.theme.md_theme_light_error
import com.example.smarttrade.ui.theme.md_theme_light_outline

@Composable
fun OutlinedText(
    value: String,
    label: String,
    upDateField: (String) -> Unit,
    errorMessage: String? = null
) {
    val isError = errorMessage != null

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = if (isError) 1.dp else 0.dp,
                    color = if (isError) md_theme_light_error else md_theme_light_outline
                ),
            value = value,
            onValueChange = upDateField,
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
            },
            isError = isError
        )
        errorMessage?.let {
            Text(
                text = it,
                color = md_theme_light_error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}