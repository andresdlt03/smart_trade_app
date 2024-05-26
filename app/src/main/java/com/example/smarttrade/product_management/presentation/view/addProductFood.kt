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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.smarttrade.R
import com.example.smarttrade.catalogue.viewmodel.catalogueViewModel
import com.example.smarttrade.components.OutlinedText
import com.example.smarttrade.gift.presentation.topBarAdd
import com.example.smarttrade.product_management.presentation.view.components.PublishProductButton
import com.example.smarttrade.product_management.presentation.viewmodel.AddProductFoodViewModel

@Composable
fun addProductFoodScreen(viewModel: AddProductFoodViewModel = hiltViewModel(),
                         navHostController: NavHostController,
                         scrollState: ScrollState,
                         vm: catalogueViewModel
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
fun addProductFood(viewModel: AddProductFoodViewModel, navHostController: NavHostController, vm: catalogueViewModel){

    val productState = viewModel.state.collectAsState()
    val foodState = viewModel.localState.collectAsState()


    var photoUri: Uri? by remember { mutableStateOf(null) }
    var photoUri1: Uri? by remember { mutableStateOf(null) }
    var photoUri2: Uri? by remember { mutableStateOf(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        photoUri = uri
    }

    topBarAdd({viewModel.goBackToCategories(navHostController)},navHostController)
    Spacer(modifier = Modifier.height(24.dp))

    OutlinedText(
        value = productState.value.name,
        label = "Nombre",
        upDateField = {viewModel.updateName(it)},
        errorMessage = productState.value.nameError
    )
    Spacer(modifier = Modifier.height(14.dp))

    OutlinedText(
        value = productState.value.description,
        label = "Descripción",
        upDateField = {viewModel.updateDescription(it)},
        errorMessage = productState.value.descriptionError
    )
    Spacer(modifier = Modifier.height(14.dp))

    OutlinedText(
        value = productState.value.dataSheet,
        label = "Ficha técnica",
        upDateField = {viewModel.updateDataSheet(it)},
        errorMessage = productState.value.dataSheetError
    )
    Spacer(modifier = Modifier.height(14.dp))

    OutlinedText(
        value = foodState.value.calories,
        label = "Calorías",
        upDateField = {viewModel.updateCalories(it)},
        errorMessage = foodState.value.caloriesError
    )
    Spacer(modifier = Modifier.height(14.dp))

    OutlinedText(
        value = productState.value.price,
        label = "Precio",
        upDateField = {viewModel.updatePrice(it)},
        errorMessage = productState.value.priceError
    )
    Spacer(modifier = Modifier.height(14.dp))

    OutlinedText(
        value = productState.value.stock,
        label = "Stock",
        upDateField = {viewModel.updateStock(it)},
        errorMessage = productState.value.stockError
    )
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
                    viewModel.updatePhotos(photoUri)
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Añade una o dos fotos del producto")
            Spacer(modifier = Modifier.height(14.dp))
            if (photoUri != null){
                AsyncImage(
                    model = photoUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
    PublishProductButton(viewModel, navHostController)
}





