package com.manuel.pueblosbonitos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.manuel.pueblosbonitos.R
import com.manuel.pueblosbonitos.navigation.AppScreens
import com.manuel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun PuebloDetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { PuebloDetailTopBar(viewModel) },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = {
            PuebloDetailBotonFlotante(viewModel)
        },
        content = { padding ->
            PuebloDetailContent(
                padding,
                viewModel,
                navController
            )
        }
    )
}

@Composable
fun PuebloDetailBotonFlotante(viewModel: MainViewModel) {
    val puebloDetail = viewModel.puebloDetail.value!!
    val esFavorito = viewModel.esFavorito
    FloatingActionButton(
        onClick = {
            viewModel.updatePueblo(puebloDetail.pueblo)
        },
        containerColor = Color.Cyan,
        shape = CircleShape,
    ) {
        Icon(
            imageVector = Icons.Filled.Star, "Botón de favoritos",
            modifier = Modifier
                .background(Color.Black)
                .padding(1.dp),
            tint =
            if (esFavorito.value)
                Color.Yellow
            else
                Color.White
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuebloDetailTopBar(viewModel: MainViewModel) {
    val puebloDetail = viewModel.puebloDetail.value!!
    TopAppBar(
        title = { Text(text = puebloDetail.pueblo.nombre) },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.purple_700),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun PuebloDetailContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val puebloDetail by viewModel.puebloDetail
    val nombreComunidad by viewModel.nombreComunidad
    Column(
        modifier = Modifier
            .background(colorResource(R.color.azulClaroFondo))
            .fillMaxSize()
            .padding(padding)
    ) {
        AsyncImage(
            model = puebloDetail!!.pueblo.imagen,
            contentDescription = "Foto de ${puebloDetail!!.pueblo.nombre}",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
        )

        Text(
            text = puebloDetail!!.pueblo.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.purple_700)),
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(
            text = "${puebloDetail!!.provincia.nombre} (${nombreComunidad})",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            color = Color.Blue,
            textAlign = TextAlign.Center
        )

        Text(
            text = "más...",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(AppScreens.WebPuebloScreen.route)
                },
            color = Color.Blue,
            textAlign = TextAlign.Center
        )
    }
}

