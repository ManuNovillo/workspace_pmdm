package com.manuel.blackjackjpc

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuel.blackjackjpc.model.Carta
import com.manuel.blackjackjpc.model.Palo


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
        title = { Text(text = "Black Jack") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.mesadepoker),
            contentDescription = "Fondo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.SpaceAround,
        content = {
            MainContent(viewModel)
        }
    )
}

@Composable
fun MainContent(viewModel: MainViewModel) {
    val cartas = viewModel.cartasJugadas
    FilaCartas(viewModel, cartas)
    Mazo(viewModel)
    Boton()
}

@Composable
fun FilaCartas(viewModel: MainViewModel, cartas: List<Carta>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        LazyRow {
            items(cartas) { carta ->
                CartaCard(carta)
            }
        }
    }

}

@Composable
fun CartaCard(carta: Carta) {
    Log.d("MANU", "CartaCard")
    val context = LocalContext.current
    val id = context.resources.getIdentifier("${carta.palo.toString().lowercase()}${carta.numero}", "drawable", context.packageName)
    Image(
        painter = painterResource(id = id),
        contentDescription = "Carta",
        modifier = Modifier.width(100.dp).height(180.dp),
    )
}

@Composable
fun Mazo(viewModel: MainViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.mazo),
            contentDescription = "Mazo",
            modifier = Modifier.width(100.dp).height(180.dp).clickable {
                onClick(viewModel)
            }
        )
    }
}

fun onClick(viewModel: MainViewModel) {
    Log.d("MANU", "KSUDFHSKDHFSHSIFHSF")
    viewModel.sacaCarta()
}

@Composable
fun Boton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {

            },
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = "STOP",
                fontSize = 20.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(MainViewModel())
}

