package com.manuel.famososjpc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = { CustomTopBar(viewModel) },
        content = { padding ->
            CustomContent(padding, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(viewModel: MainViewModel) {
    val racha by viewModel.racha
    // CenterAlignedTopAppBar es como una TopAppBar pero centrada
    // La TopAppBar normal está un poco desplazada a la derecha
    CenterAlignedTopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
            ) {
                Text(
                    text = "Famosos",
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Start,
                )

                Text(
                    text = "Racha: $racha",
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.End,
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
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        for (i in 0 until viewModel.numeroFamosos) {
            CardFamosos(i, viewModel)
        }
    }
}

@Composable
fun CardFamosos(i: Int, viewModel: MainViewModel) {
    val personajesShow = viewModel.personajesShow
    val coloresFotos = viewModel.coloresFotos
    val coloresNombres = viewModel.coloresNombres
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(12.dp),
    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(
                    id = personajesShow[i].id.toInt()
                ),
                contentDescription = "Famoso",
                modifier = Modifier
                    .weight(3f)
                    .size(80.dp, 120.dp)
                    .background(coloresFotos[i])
                    .padding(8.dp)
                    .clickable {
                        if (coloresFotos[i] != Color.Green) viewModel.onClickFoto(i)
                    }
            )
            Text(
                text = personajesShow[i].nombre,
                modifier = Modifier

                    .weight(9f)
                    .padding(16.dp)
                    .background(coloresNombres[i])
                    .clickable { if (coloresNombres[i] != Color.Green) viewModel.onClickName(i) },
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
    }
}

