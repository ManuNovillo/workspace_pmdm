package com.manuel.pueblosbonitos.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.manuel.pueblosbonitos.R
import com.manuel.pueblosbonitos.model.entities.Comunidad
import com.manuel.pueblosbonitos.ui.theme.AzulClaroFondo
import com.manuel.pueblosbonitos.ui.theme.MoradoFondo
import com.manuel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun MainScreen( navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MainTopBar() },
        content = { padding -> MainContent(padding, viewModel, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        title = { Text(text = "Comunidades") },
        colors = topAppBarColors(
            containerColor = MoradoFondo,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun MainContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val comunidades = viewModel.comunidades
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MoradoFondo)
    ) {
        LazyColumn {
            items(comunidades) { comunidad ->
                MainCardComunidad(comunidad, viewModel, navController)
            }
        }
    }
}

@Composable
fun MainCardComunidad(
    comunidad: Comunidad,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        colors = cardColors(
            containerColor = AzulClaroFondo
        ),
        onClick = {
            viewModel.prepararPueblosScreen(comunidad)
            navController.navigate("PueblosScreen")
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(width = 100.dp, height = 80.dp)
                    .weight(2f)
            )
            Text(
                text = comunidad.nombre,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(4f),
                color = Color.Blue
            )
        }
    }
}


