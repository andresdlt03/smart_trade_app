import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen() {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AYUDA", fontSize = 40.sp,fontWeight = FontWeight.Bold, fontFamily = FontFamily.Default) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            SectionTitle(title = "Inicio")
            Text("Bienvenido a SmartTrade. Esta aplicación te permetirá comprar o vender productos de una manera mucho más sostenible. Aquí encontrarás una guía sobre cómo utilizar las diferentes funciones de la aplicación.")

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(title = "Funciones Principales")

            ExpandableFunctionCard(
                functionName = "Ver un producto",
                description = "Visualizar los datos completos de un producto",
                steps = listOf(
                    "Paso 1: Encuentra el producto que deseas visualizar en el catalogo.",
                    "Paso 2: Selecciona el producto haciendo click sobre cualquier parte del mismo.",
                    "Paso 3: Visualiza los datos, si deseas ver la información técnica, debes hacer click en el boton 'Ver datasheet'."
                )
            )

            ExpandableFunctionCard(
                functionName = "Mover productos a listas",
                description = "Mover productos entre las listas de deseados, guardar para más tarde o el carrito de compra.",
                steps = listOf(
                    "Paso 1: Visualiza el producto que quieras mover.",
                    "Paso 2: Seleciona la lista a la que desees moverlo haciendo click en los botones inferiores.",
                    "Paso 3: Ve a ese boton desde la barra general inferior y podrás ver el producto en la lista seleccionada."
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(title = "Preguntas Frecuentes (FAQ)")
            FAQItem(
                question = "¿Debo registrar mi tarjeta de credito siendo comprador?",
                answer = "No es obligatorio poner la tarjeta de crédito a la hora de registrarse, sin embargo, a la hora de comprar un producto, si no se ha registrado posteriormente, se le pedirá una tarjeta válida."
            )
            FAQItem(
                question = "¿Que diferencias existen entre la lista de deseados y guardar para más tarde?",
                answer = "La lista de deseados es algo que simplemente te gusta, sin embargo, guardar para más tarde es algo que más tarde vas a comprar pero en el momento de pagar lo 'apartas' para comprarlo otro día, pudiendo subirlo al carrito en el momento que se desee."
            )
            FAQItem(
                question = "¿Puedo mover un producto de cualquier lista al carrito?",
                answer = "Si, es posible mover cualquier producto al carrito de compra para poder comprarlo desde cualquier otra lista en la que se encuentre tu producto mediante el boton de 'Mover al carrito'."
            )
            FAQItem(
                question = "¿Si publico un producto en venta aparecerá automáticamente en el catálogo?",
                answer = "No, un producto recién subido tendrá que ser aceptado por alguno de nuestros administradores para poder aparecer en el catálogo general."
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
    )
}

@Composable
fun ExpandableFunctionCard(functionName: String, description: String, steps: List<String>) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = functionName,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                steps.forEach { step ->
                    Text(text = step, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            } else {
                Text(text = description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFE0E0E0), shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Text(
            text = question,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(text = answer, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun HelpScreenPreview() {
    MaterialTheme {
        HelpScreen()
    }
}