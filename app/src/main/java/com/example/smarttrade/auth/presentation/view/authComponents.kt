package com.example.smarttrade.auth.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.smarttrade.R


/*@Composable
fun outlinedText(value:String, label:String, upDateField:(String) -> Unit){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {upDateField(it) },
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
}*/
@Composable
fun LogoImage(){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.logo_description),
        modifier = Modifier.size(100.dp)
    )
}