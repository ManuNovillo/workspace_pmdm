package com.manuel.famososjpc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
        topBar = { CustomTopBar() },
        content = { padding ->
            CustomContent(padding, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text(text = "Famosos") },
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
    val personajesShow by viewModel.personajesShow.observeAsState()
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
                .padding(horizontal = 10.dp ,vertical = 8.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(
                    id = viewModel.mainActivity.resources.getIdentifier(
                        "p${personajesShow!![i].id}", "drawable", viewModel.mainActivity.packageName
                    )
                ),
                contentDescription = "Famoso",
                modifier = Modifier
                    .weight(3f)
                    .size(80.dp,120.dp)
                    .background(coloresFotos[i])
                    .padding(8.dp)
                    .clickable {
                        if (coloresFotos[i] != Color.Green) viewModel.clickFoto(i)
                    }
            )
            Text(
                text = personajesShow!![i].nombre,
                modifier = Modifier

                    .weight(9f)
                    .padding(16.dp)
                    .background(coloresNombres[i])
                    .clickable { if (coloresNombres[i] != Color.Green) viewModel.clickName(i) },
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )

        }
    }
}

