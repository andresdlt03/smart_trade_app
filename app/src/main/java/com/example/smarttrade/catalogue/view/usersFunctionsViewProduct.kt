package com.example.smarttrade.catalogue.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttrade.catalogue.viewmodel.catalogueViewModel


@Composable
fun clientViewProduct(viewModel: catalogueViewModel, number: Int) {
    var iconPressed by remember { mutableStateOf(false) }
    val product = viewModel.getProduct()
    if (product != null) {
        iconPressed = objetcLists.wishList.containsItem(product)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (iconPressed) {
            BottomBarItem(icon = Icons.Filled.Favorite, onClick = {
                iconPressed = !iconPressed;
                var aux = viewModel.getProduct()
                if(aux != null)objetcLists.wishList.removeItem(aux)
            })
        } else {
            BottomBarItem(icon = Icons.Filled.FavoriteBorder, onClick = {
                iconPressed = !iconPressed;
                var aux = viewModel.getProduct()
                if(aux != null)objetcLists.wishList.addItem(aux)
            })
        }
        BottomBarItem(icon = Icons.Filled.AddCircle, onClick = {
            var aux = viewModel.getProduct()
            if(aux != null)objetcLists.forLaterList.addItem(aux)
        })
        BottomBarItem(icon = Icons.Filled.ShoppingCart) {
            var aux = viewModel.getProduct()
            for (i  in 1..number) {
                if (aux != null) objetcLists.shoppingCart.addItem(aux)
            }
        }
    }
}

@Composable
fun BottomBarItem(
    icon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )

    }
}

@Composable
fun adminViewProduct() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomBarItem2(text = "Aprobar", onClick = { }, color = Color.Green)
        BottomBarItem2(text = "Mantener sin aprobar", onClick = { }, color = Color.Red)
    }
}

@Composable
fun BottomBarItem2(
    text: String,
    onClick: () -> Unit,
    color: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(contentColor = Color.White)
    ) {
        Text(text = text)
    }
}


@Composable
fun sellerViewProduct() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { },
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Create,
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Editar",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        )
    }
}





@Composable
fun alertBox(
    onDismissRequest: () -> Unit,
    onConfirmClicked: (String, String) -> Unit,
    p: String,
    s: String,
){
    var price by remember { mutableStateOf(p) }
    var stock by remember { mutableStateOf(s) }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Editar Producto",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)},
        text = {
            Column {
                OutlinedTextField(
                    value = price,
                    onValueChange = { newPrice ->
                        price = newPrice

                    },
                    label = { Text("Precio") },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = stock,
                    onValueChange = { newStock ->
                        stock = newStock

                    },
                    label = { Text("Stock") }
                )
            }

        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmClicked(price, stock)
                    onDismissRequest()
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text("Confirmar")
            }

        },
        dismissButton = {
            Button(
                onClick = onDismissRequest,
            ) {
                Text("Cancelar")
            }
        }
    )
}