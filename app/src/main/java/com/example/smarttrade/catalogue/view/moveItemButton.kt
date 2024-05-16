package com.example.smarttrade.catalogue.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smarttrade.catalogue.view.objetcLists
import com.example.smarttrade.catalogue.viewmodel.Product
import kotlinx.coroutines.delay
import java.lang.reflect.Modifier

object moveItemButton {
    val masTarde = "masTarde"
    val deseado = "deseado"
    val carrito = "carrito"
}
@Composable
fun MoveItemButton(
    sourceListName: String,
    destinationListName: String,
    item: Product,
    navController: NavHostController
) {
    var showMessage by remember { mutableStateOf(false) }

    Button(onClick = {
        when (sourceListName) {
            "carrito" -> objetcLists.ListaCarrito.removeItem(item)
            "masTarde" -> objetcLists.ListaGuardarTarde.removeItem(item)
            "deseado" -> objetcLists.ListaDeseados.removeItem(item)
        }

        when (destinationListName) {
            "carrito" -> objetcLists.ListaCarrito.addItem(item)
            "masTarde" -> objetcLists.ListaGuardarTarde.addItem(item)
            "deseado" -> objetcLists.ListaDeseados.addItem(item)
        }

        navController.navigate(sourceListName)
    }) {
        Text("Mover a $destinationListName")
    }

    TemporaryMessage(
        message = "Elemento movido correctamente",
        visible = showMessage
    )
}

@Composable
fun RemoveItemButton(
    sourceListName: String,
    item: Product,
    navController: NavHostController
) {
    var showMessage by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Button(onClick = {
        showDialog = true
    }) {
        Text("Eliminar")
    }

    if (showDialog) {
        ConfirmationDialog(
            onConfirm = {
                when (sourceListName) {
                    "carrito" -> objetcLists.ListaCarrito.removeItem(item)
                    "masTarde" -> objetcLists.ListaGuardarTarde.removeItem(item)
                    "deseado" -> objetcLists.ListaDeseados.removeItem(item)
                }
                showMessage = true
                navController.navigate(sourceListName)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

    TemporaryMessage(
        message = "Elemento eliminado correctamente",
        visible = showMessage
    )
}

@Composable
fun ConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirmar eliminación") },
        text = { Text("¿Estás seguro de que quieres eliminar este elemento?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Sí")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}

@Composable
fun TemporaryMessage(
    message: String,
    visible: Boolean,
    durationMillis: Long = 3000
) {
    var showMessage by remember { mutableStateOf(visible) }

    if (showMessage) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(durationMillis)
            showMessage = false
        }
        Text(
            text = message,
            color = Color.Black,
            modifier = androidx.compose.ui.Modifier
                .padding(8.dp)
        )
    }
}

