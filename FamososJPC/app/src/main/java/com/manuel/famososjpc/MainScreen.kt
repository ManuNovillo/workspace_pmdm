package com.manuel.famososjpc

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


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
        verticalArrangement = Arrangement.Center
    )
    {
        for (i in 0 until viewModel.numeroFamosos) {
            RowFamosos(i, viewModel)
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun RowFamosos(i: Int, viewModel: MainViewModel) {
    val personajesShow by viewModel.personajesShow.observeAsState()
    val colores = viewModel.colores
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = viewModel.mainActivity.resources.getIdentifier(
                "p${personajesShow!![i].id}", "drawable", viewModel.mainActivity.packageName)),
            contentDescription = "Famoso",
            modifier = Modifier
                .background(colores[i])
                .height(100.dp)
                .clickable {
                    viewModel.clickFoto(i)
                }
        )
        Text(
            text = personajesShow!![i].nombre,
            Modifier
                .background(colores[i])
                .clickable { viewModel.clickName(i) },
        )
    }
}

