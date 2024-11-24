package com.manuel.pueblosbonitos.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manuel.pueblosbonitos.model.entities.PuebloConProvincia
import com.manuel.pueblosbonitos.ui.theme.AzulClaroFondo
import com.manuel.pueblosbonitos.ui.theme.MoradoFondo
import com.manuel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun PueblosScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { PueblosTopBar(viewModel) },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            PueblosBotonFlotante(viewModel)
        },
        content = { padding ->
            PueblosContent(
                padding = padding,
                viewModel = viewModel,
                navController = navController
            )
        }
    )
}

@Composable
fun PueblosBotonFlotante(viewModel: MainViewModel) {
    val mostrarSoloFavoritos = viewModel.mostrarSoloFavoritos.value
    FloatingActionButton(
        onClick = {
            viewModel.toggleFavoritos()
        },
        containerColor = Color.Cyan,
        shape = CircleShape,
    ) {
        Icon(
            imageVector =  Icons.Filled.Star, "Botón de favoritos",
            modifier = Modifier
                .background(Color.Black)
                .padding(1.dp),
            tint =
                if (mostrarSoloFavoritos)
                    Color.Yellow
                else
                    Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PueblosTopBar(viewModel: MainViewModel) {
    val nombreComunidad by viewModel.nombreComunidad
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = nombreComunidad)
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "dada"
                )
            }
        },

        colors = topAppBarColors(
            containerColor = MoradoFondo,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun PueblosContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val pueblosConProvincia = viewModel.pueblosConProvincia
    val mostrarSoloFavoritos = viewModel.mostrarSoloFavoritos
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MoradoFondo)

    ) {
        LazyColumn {
            if (mostrarSoloFavoritos.value) {
                items(pueblosConProvincia.filter { it.pueblo.fav == 1 }) { pueblo ->
                    PueblosCard(pueblo, viewModel, navController)
                }
            } else {
                items(pueblosConProvincia) { pueblo ->
                    PueblosCard(pueblo, viewModel, navController)
                }
            }
        }
    }

}

@Composable
fun PueblosCard(
    puebloConProvincia: PuebloConProvincia,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        colors = cardColors(
            containerColor = AzulClaroFondo
        ),
        onClick = {
            viewModel.prepararPuebloDetailScreen(puebloConProvincia)
            navController.navigate("PuebloDetailScreen")
        }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(viewModel.mainActivity.baseContext)
                    .data(puebloConProvincia.pueblo.imagen)
                    .build(),
                contentDescription = "Foto de ${puebloConProvincia.pueblo.nombre}",
                modifier = Modifier
                    .height(120.dp)
                    .weight(2f)

            )
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = puebloConProvincia.pueblo.nombre,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .background(Color.Blue),
                    color = Color.White
                )

                Text(
                    text = puebloConProvincia.provincia.nombre,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    color = Color.Blue
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Icono estrella",
                        tint = if (puebloConProvincia.pueblo.fav == 1)
                            Color.Yellow
                        else
                            Color.Gray
                    )
                }
            }
        }
    }
}
