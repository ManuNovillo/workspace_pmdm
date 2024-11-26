package com.manuel.acbroom.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.manuel.acbroom.R
import com.manuel.acbroom.viewmodel.MainViewModel


@Composable
fun JugadorScreen(viewModel: MainViewModel) {
    Scaffold(
        content = { padding -> JugadorContent(padding, viewModel) }

    )
}

@Composable
fun JugadorContent(padding: PaddingValues, viewModel: MainViewModel) {
    val likes = viewModel.jugadorConEquipo.value!!.jugador.likes
    val jugadorConEquipo by viewModel.jugadorConEquipo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
        ) {
            AsyncImage(
                model = jugadorConEquipo!!.jugador.imagen,
                contentDescription = jugadorConEquipo!!.jugador.nombre,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            )
            {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Ver página de jugador",
                    modifier = Modifier
                        .padding(end = 8.dp, top = 8.dp)
                        .size(40.dp)
                )
            }
        }
        Text(
            text = jugadorConEquipo!!.jugador.nombre,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.azuloscuro))
                .padding(8.dp),
            color = colorResource(R.color.azulclaro),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = jugadorConEquipo!!.equipo.nombre,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Ver página de jugador",
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Text(
            text = jugadorConEquipo!!.jugador.pais,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .size(40.dp)
            )
            Text(
                text = "${jugadorConEquipo!!.jugador.edad} años - ${jugadorConEquipo!!.jugador.estatura} m",
                textAlign = TextAlign.Center,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ThumbUp,
                    contentDescription = "Numero likes",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp)
                        .clickable {
                            viewModel.updateLikes(jugadorConEquipo!!.jugador)
                        }
                )
                Text(
                    text = jugadorConEquipo!!.jugador.likes.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

        }
    }
}