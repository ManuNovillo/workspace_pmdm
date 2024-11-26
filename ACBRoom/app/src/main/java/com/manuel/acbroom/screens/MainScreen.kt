package com.manuel.acbroom.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.manuel.acbroom.R
import com.manuel.acbroom.model.Jugador
import com.manuel.acbroom.model.JugadorConEquipo
import com.manuel.acbroom.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {

    val texto = remember { mutableStateOf("") }
    Scaffold(
        topBar = { MainTopBar(viewModel, texto) },
        content = { padding -> MainContent(padding, viewModel, texto, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(viewModel: MainViewModel, texto: MutableState<String>) {
    val icono by viewModel.icono
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "ACB")
                SearchView(texto)
                Icon(
                    painter = painterResource(icono),
                    contentDescription = "lupa",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.changeIcono()
                        },

                )
            }
        },

        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun SearchView(
    estado: MutableState<String>
) {
    TextField(
        value = estado.value,
        onValueChange = { textoNuevo ->
            estado.value = textoNuevo
        },
        modifier = Modifier
            .padding(2.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
        maxLines = 1,
        singleLine = true,
        leadingIcon = {Icon(Icons.Filled.Search, contentDescription = "lupa")},
        placeholder = {
            Text(
                text = "Buscar jugador...",
                fontSize = 20.sp
            )
        }
    )
}

@Composable
fun MainContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    texto: MutableState<String>,
    navController: NavHostController) {
    val jugadoresConEquipo = viewModel.jugadoresConEquipo
    val isPlayer by viewModel.isPlayer
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn {
            val listaFiltrada = jugadoresConEquipo.filter { jugadorConEquipo ->
                if (isPlayer)
                    jugadorConEquipo.jugador.nombre.contains(texto.value, true)
                else
                    jugadorConEquipo.equipo.nombre.contains(texto.value, true)
            }
            items(listaFiltrada) { jugadorConEquipo ->
                MainJugadorCard(jugadorConEquipo, viewModel, navController)
            }
        }
    }
}

@Composable
fun MainJugadorCard(jugadorConEquipo: JugadorConEquipo, viewModel: MainViewModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(8.dp),
        colors = cardColors(
            containerColor = colorResource(R.color.azulclaro)
        ),
        onClick = {
            viewModel.prepararJugadorScreen(jugadorConEquipo)
            navController.navigate("JugadorScreen")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            AsyncImage(
                model = jugadorConEquipo.jugador.imagen,
                contentDescription = jugadorConEquipo.jugador.nombre,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .background(colorResource(R.color.azulclaro))
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = jugadorConEquipo.jugador.nombre,
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.azulclaro),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.azuloscuro))

                )
                RowLikes(
                    jugadorConEquipo.jugador,
                    viewModel

                )
                Text(
                    text = jugadorConEquipo.jugador.pais,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
                Text(
                    text = jugadorConEquipo.equipo.nombre,
                    textAlign = TextAlign.End,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun RowLikes(jugador: Jugador, viewModel: MainViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = jugador.nombre,
        )
        Text(
            text = jugador.likes.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}
