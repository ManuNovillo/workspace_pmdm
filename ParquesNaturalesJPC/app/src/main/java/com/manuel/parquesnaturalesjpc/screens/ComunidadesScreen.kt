package com.manuel.parquesnaturalesjpc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalWithComputedDefaultOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.manuel.parquesnaturalesjpc.R
import com.manuel.parquesnaturalesjpc.model.Comunidad
import com.manuel.parquesnaturalesjpc.navigation.AppScreens
import com.manuel.parquesnaturalesjpc.viewmodel.MainViewModel

@Composable
fun ComunidadesScreen(viewModel: MainViewModel, navController: NavHostController) {
    Scaffold(
        topBar = { ComunidadesTopBar() },
        content = { padding -> ComunidadesContent(viewModel, navController, padding) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComunidadesTopBar() {
    TopAppBar(
        title = { Text(text = "Parques Naturales") },
    )
}

@Composable
fun ComunidadesContent(
    viewModel: MainViewModel,
    navController: NavHostController,
    padding: PaddingValues
) {
    val comunidades = viewModel.comunidades
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn {
            items(comunidades) { comunidad ->
                CardComunidad(comunidad, viewModel, navController)
            }
        }
    }
}

@Composable
fun CardComunidad(
    comunidad: Comunidad,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            viewModel.loadParquesComunidad(comunidad)
            navController.navigate(AppScreens.ParquesScreen.route)
        },
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            val id = LocalContext.current.resources.getIdentifier(
                "c${comunidad.id}",
                "drawable",
                LocalContext.current.packageName
            )
            Image(
                painter = painterResource(id),
                contentDescription = "Imagen de ${comunidad.nombre}",
            )
            Text(
                text = comunidad.nombre,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
