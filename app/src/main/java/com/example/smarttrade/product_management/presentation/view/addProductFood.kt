package com.example.smarttrade.product_management.presentation.view

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.viewmodel.catalogueViewModel
import com.example.smarttrade.catalogue.viewmodel.sellerCatalogueViewModel
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductFoodViewModel

@Composable
fun addProductFoodScreen(viewModel: AddProductFoodViewModel = hiltViewModel(),
                         navHostController: NavHostController,
                         scrollState: ScrollState,
                         vm: sellerCatalogueViewModel
                         ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(32.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        addProductFood(viewModel, navHostController, vm)
    }
}

@Composable
fun addProductFood(viewModel: AddProductFoodViewModel, navHostController: NavHostController, vm: sellerCatalogueViewModel){

    val state = viewModel.state.collectAsState()


    var photoUri: Uri? by remember { mutableStateOf(null) }
    var photoUri1: Uri? by remember { mutableStateOf(null) }
    var photoUri2: Uri? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        photoUri = uri
    }

    topBarAdd({viewModel.goBackCategories(navHostController)},navHostController)
    Spacer(modifier = Modifier.height(24.dp))
    outLinedTextAdd(state.value.name, "Nombre (20 carácteres)", 1,{viewModel.onItemChanged(it,1)} ,{ viewModel.clearSelected(1) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(state.value.description, "Descripción (50 carácteres)", 2,{viewModel.onItemChanged(it,2)} ,{ viewModel.clearSelected(2) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(state.value.calories, "Calorias", 3,{viewModel.onItemChanged(it,3)} ,{ viewModel.clearSelected(3) })
    Spacer(modifier = Modifier.height(14.dp))
    outLinedTextAdd(state.value.price, "Precio del producto", 4,{viewModel.onItemChanged(it,4)} ,{ viewModel.clearSelected(4) })
    Spacer(modifier = Modifier.height(54.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = {
                    launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add_foto),
                    contentDescription = null
                )
                if (photoUri != null) {
                    if(photoUri1 == null && photoUri2 == null){photoUri1 = photoUri; viewModel.updatePhotos(photoUri1, photoUri2)}
                    if(photoUri1 != null && photoUri2 == null && photoUri1 != photoUri){photoUri2 = photoUri; viewModel.updatePhotos(photoUri1, photoUri2)}
                    if(photoUri2 != photoUri && photoUri1 != photoUri && photoUri2 != photoUri1){photoUri2 = photoUri1; photoUri1 = photoUri; viewModel.updatePhotos(photoUri1, photoUri2) }
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Añade una o dos fotos del producto")
            Spacer(modifier = Modifier.height(14.dp))
            if (photoUri1 != null){
                AsyncImage(
                    model = photoUri1,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            if (photoUri2 != null){
                AsyncImage(
                    model = photoUri2,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(34.dp))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = state.value.textError,
            color = Color.Red
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    publishProductButton2(viewModel, vm, navHostController)
}





