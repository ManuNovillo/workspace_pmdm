package net.azarquiel.examenmanuelnovillo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import net.azarquiel.examenmanuelnovillo.R
import net.azarquiel.examenmanuelnovillo.model.CostaConPlayas
import net.azarquiel.examenmanuelnovillo.model.Playa
import net.azarquiel.examenmanuelnovillo.viewmodel.MainViewModel

@Composable
fun PlayasScreen(viewModel: MainViewModel) {
    val costaConPlayas by viewModel.costaSeleccionada.observeAsState()
    val soloAzules by viewModel.soloAzules.observeAsState(false)
    Scaffold(
        topBar = { DetailTopBar(costaConPlayas!!) },
        content = { padding -> PlayasContent(costaConPlayas!!, soloAzules, padding) },
        floatingActionButton = { BotonFlotante(soloAzules, viewModel) },
        floatingActionButtonPosition = FabPosition.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayasTopBar(costaConPlayas: CostaConPlayas) {
    TopAppBar(
        title = { Text(text = costaConPlayas.costa.nombre) },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.morado_claro),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun PlayasContent(
    costaConPlayas: CostaConPlayas,
    soloAzules: Boolean,
    padding: PaddingValues,
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        SearchView(textState)
        val playasFiltradas = costaConPlayas.playas.filter { playa ->
            playa.nombre.contains(textState.value.text, ignoreCase = true) &&
                    (soloAzules && playa.azul == 1 || !soloAzules)
        }
        LazyColumn {
            items(playasFiltradas) { playa ->
                PlayaCard(playa)
            }
        }
    }
}


@Composable
fun SearchView(
    state: MutableState<TextFieldValue>
) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
        placeholder = {
            Text(text = "Search here...")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = Color.White,
        ),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon")
        },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )
}

@Composable
fun PlayaCard(
    playa: Playa
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        colors = cardColors(
            containerColor = colorResource(R.color.azul_oscuro)
        )
    ) {
        Column {
            AsyncImage(
                model = playa.imagen,
                contentDescription = playa.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.FillBounds
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = playa.nombre,
                    color = colorResource(R.color.azul_claro),
                    fontSize = 25.sp,
                    lineHeight = 35.sp,
                    modifier = Modifier
                        .weight(1f)
                )

                if (playa.azul == 1) {
                    Image(
                        painter = painterResource(R.drawable.bazul),
                        contentDescription = "Playa azul",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun BotonFlotante(soloAzules: Boolean, viewModel: MainViewModel) {
    FloatingActionButton(
        shape = RoundedCornerShape(20),
        containerColor = colorResource(R.color.azul_oscuro),
        modifier = Modifier
            .padding(20.dp),
        onClick = {
            viewModel.alternarMostarSoloAzules()
        }) {
        Image(
            painter = painterResource(if (soloAzules) R.drawable.bazul else R.drawable.bnoazul),
            contentDescription = "Filtrar playas azules",
            modifier = Modifier
                .size(50.dp)
        )
    }
}