package com.manuel.acbroom.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.manuel.acbroom.model.Jugador
import com.manuel.acbroom.model.JugadorConEquipo
import com.manuel.acbroom.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MainTopBar() },
        content = { padding -> MainContent(padding, viewModel) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        title = { Text(text = "Jugadores") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun MainContent(padding: PaddingValues, viewModel: MainViewModel) {
    val jugadoresConEquipo = viewModel.jugadoresConEquipo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn {
            items(jugadoresConEquipo) { jugadorConEquipo ->
                MainJugadorCard(jugadorConEquipo)
            }
        }
    }
}

@Composable
fun MainJugadorCard(jugadorConEquipo: JugadorConEquipo) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        colors = cardColors(
            containerColor = Color.Cyan,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = jugadorConEquipo.jugador.imagen,
                contentDescription = jugadorConEquipo.jugador.nombre,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Column(
                modifier = Modifier
                    .weight(2f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = jugadorConEquipo.jugador.nombre,
                    textAlign = TextAlign.Center
                )
                RowLikes(jugadorConEquipo.jugador)

                Text(
                    text = jugadorConEquipo.jugador.pais,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = jugadorConEquipo.equipo.nombre,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun RowLikes(jugador: Jugador) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = jugador.nombre,
        )
        Text(
            text = jugador.likes.toString(),
            textAlign = TextAlign.Center
        )
    }
}
