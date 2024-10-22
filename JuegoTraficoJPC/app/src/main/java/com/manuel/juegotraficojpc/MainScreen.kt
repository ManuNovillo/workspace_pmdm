package com.manuel.juegotraficojpc

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuel.juegotraficojpc.model.Sign

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
        title = { Text(text = "Señales") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    val signBuscar = viewModel.signBuscar.observeAsState(Sign())
    val jugadaFotos = viewModel.jugadaFotos.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        )
    {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(1F),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Puntos: 0",
                fontSize = 30.sp
            )
            Text(
                text = "Aciertos: 0",
                fontSize = 30.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(4F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = signBuscar.value.descripcion,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4F)
        ) {
            for (i in 0 until 2) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    for (j in 0 until 2) {
                        Image(
                            painter = painterResource(id = jugadaFotos.value!![if (i == 1) i+j+1 else j]),
                            contentDescription = "Sí",
                            modifier = Modifier
                                .width(150.dp)
                                .height(90.dp)
                                .padding(5.dp)
                                .clickable { viewModel.onClickSign(if (i == 1) i+j+1 else j) }
                        )
                    }
                }
            }
        }
    }
}

