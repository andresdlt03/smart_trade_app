package com.example.smarttrade.product_management.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.smarttrade.R

@Composable
fun addProductTechnologyScreen(viewModel: addProductTechnologyViewModel, navHostController: NavHostController,scrollState: ScrollState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        addProductTechnology(viewModel, navHostController)
    }
}

@Composable
fun addProductTechnology(viewModel: `addProductTechnologyViewModel`, navHostController: NavHostController){

    val name :String by viewModel.name.observeAsState(initial = "")
    val description :String by viewModel.description.observeAsState(initial = "")
    val model :String by viewModel.model.observeAsState(initial = "")
    val energy :String by viewModel.energy.observeAsState(initial = "")
    val price :String by viewModel.price.observeAsState(initial = "")
    var photoUri: Uri? by remember { mutableStateOf(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        photoUri = uri
    }



    topBarAdd({viewModel.goBackCategories(navHostController)},navHostController)
    Spacer(modifier = Modifier.height(24.dp))
    outLinedTextAdd(name, "Nombre (20 carácteres)", 1,{viewModel.onItemChanged(it,1)} ,{ viewModel.clearSelected(1) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(description, "Descripción (50 carácteres)", 2,{viewModel.onItemChanged(it,2)} ,{ viewModel.clearSelected(2) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(model, "Modelo", 3,{viewModel.onItemChanged(it,3)} ,{ viewModel.clearSelected(3) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(energy, "Consumo energético (kWh)", 4,{viewModel.onItemChanged(it,4)} ,{ viewModel.clearSelected(4) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(price, "Precio", 5,{viewModel.onItemChanged(it,5)} ,{ viewModel.clearSelected(5) })
    Spacer(modifier = Modifier.height(514.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = {launcher.launch(
                    PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
                ) },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_foto),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Añade una foto del producto")
        }
    }

    Spacer(modifier = Modifier.height(54.dp))
    if (photoUri != null) {
        val painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(LocalContext.current)
                .data(data = photoUri)
                .build()
        )

        Image(
            painter = painter,
            contentDescription = null

        )
    }
    publishProductButton()

}
