package com.manuel.parquesnaturalesjpc.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.manuel.parquesnaturalesjpc.model.Comunidad
import com.manuel.parquesnaturalesjpc.model.Parque
import com.manuel.parquesnaturalesjpc.navigation.AppScreens
import com.manuel.parquesnaturalesjpc.viewmodel.MainViewModel

@Composable
fun ParquesScreen(viewModel: MainViewModel, navController: NavHostController) {
    Scaffold(
        topBar = { ParquesTopBar(viewModel.comunidadSeleccionada) },
        content = {padding -> ParquesContent(viewModel, navController, padding)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParquesTopBar(comunidad: Comunidad) {
    TopAppBar(
        title = { Text(text = comunidad.nombre) },
    )
}

@Composable
fun ParquesContent(viewModel: MainViewModel, navController: NavHostController, padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn {
            items(viewModel.parques) { parque ->
                ParquesCard(parque, viewModel, navController)
            }
        }
    }
}

@Composable
fun ParquesCard(
    parque: Parque,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            viewModel.loadParque(parque)
            navController.navigate(AppScreens.DetailScreen.route)
        },
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                model = parque.imagen,
                contentDescription = "Imagen de ${parque.nombre}",
            )

            Text(
                text = parque.nombre,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
