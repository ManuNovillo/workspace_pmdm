package com.manuel.parquesnaturalesjpc.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.manuel.parquesnaturalesjpc.R
import com.manuel.parquesnaturalesjpc.model.Parque
import com.manuel.parquesnaturalesjpc.viewmodel.MainViewModel

@Composable
fun DetailScreen(viewModel: MainViewModel, navController: NavHostController) {
    val likes = remember { mutableIntStateOf(viewModel.parqueSeleccionado.likes) }
    Scaffold(
        topBar = { DetailTopBar(viewModel.parqueSeleccionado, likes) },
        content = { padding -> DetailContent(viewModel, navController, padding) },
        floatingActionButton = { DetailFAB(likes) }
    )
}

@Composable
fun DetailFAB(likes: MutableIntState) {
    FloatingActionButton(
        onClick = {
            likes.intValue++
        }
    ) {
        Image(
            painter = painterResource(R.drawable.thumbs),
            contentDescription = "Icono de me gusta",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(parque: Parque, likes: MutableIntState) {
    TopAppBar(
        title = {
            Row {
                Text(
                    text = parque.nombre,
                    modifier = Modifier
                        .weight(2f)
                )
                Row(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(likes.intValue.toString())
                    Image(
                        painter = painterResource(R.drawable.thumbs),
                        contentDescription = "Icono de me gusta",
                    )
                }
            }
        }
    )
}

@Composable
fun DetailContent(
    viewModel: MainViewModel,
    navController: NavHostController,
    padding: PaddingValues
) {
    val parque = viewModel.parqueSeleccionado
    val imagenes = viewModel.imagenesParque
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(
            AnnotatedString.fromHtml(
                parque.descripcion,
            )
        )
        LazyRow {
            items(imagenes) { imagen ->
                AsyncImage(
                    model = imagen.foto,
                    contentDescription = "Foto de ${parque.nombre}",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            AsyncImage(
                model = parque.fondo,
                contentDescription = "Fondo de ${parque.nombre}"
            )
            AsyncImage(
                model = parque.mapa,
                contentDescription = "Mapa de ${parque.nombre}"
            )
        }
    }
}
