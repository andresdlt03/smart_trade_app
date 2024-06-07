package com.example.smarttrade.catalogue.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.data.repository.ProductWrapper
import com.example.smarttrade.catalogue.presentation.viewmodel.HomeCatalogueViewModel
import com.example.smarttrade.singleton.UserLogged

@Composable
fun ProductItem(
    product: ProductWrapper,
    viewModel: HomeCatalogueViewModel = hiltViewModel(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                viewModel.openProductDetails(product)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(72.dp),
            )
            Column() {
                Text(
                    text = product.product.name,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = product.product.price.toString(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = product.product.description,
                    color = Color.Black,
                    maxLines = 2
                )
            }
        }

        if(UserLogged.userType == "admin") {
            VerifyButtons(product.product.name)
        }

    }
}

@Composable
fun VerifyButtons(
    name: String,
    viewModel: HomeCatalogueViewModel = hiltViewModel(),
) {
    Row{
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clickable { viewModel.verifyProduct(name) },
            tint = Color.Green,
            imageVector = Icons.Default.Check,
            contentDescription = "Check",
        )
        Icon(
            modifier = Modifier
                .size(48.dp),
            tint = Color.Red,
            imageVector = Icons.Default.Clear,
            contentDescription = "Clear",
        )
    }
}