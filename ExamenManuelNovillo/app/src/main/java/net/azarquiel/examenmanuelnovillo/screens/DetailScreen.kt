package net.azarquiel.examenmanuelnovillo.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.examenmanuelnovillo.R
import net.azarquiel.examenmanuelnovillo.model.CostaConPlayas
import net.azarquiel.examenmanuelnovillo.navigation.AppScreens
import net.azarquiel.examenmanuelnovillo.viewmodel.MainViewModel
import androidx.compose.ui.text.input.TextFieldValue as TextFieldValue1

@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    val costaConPlayas by viewModel.costaSeleccionada.observeAsState()
    Scaffold(
        topBar = { DetailTopBar(costaConPlayas!!) },
        content = { padding -> DetailContent(costaConPlayas!!, padding, viewModel, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(costaConPlayas: CostaConPlayas) {
    TopAppBar(
        title = { Text(text = costaConPlayas.costa.nombre) },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.morado_claro),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun DetailContent(
    costaConPlayas: CostaConPlayas,
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(colorResource(R.color.azul_claro))
            .verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            model = costaConPlayas.costa.imagen,
            contentDescription = costaConPlayas.costa.nombre,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = costaConPlayas.costa.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.azul_oscuro))
                .padding(10.dp),
            color = colorResource(R.color.azul_claro),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            lineHeight = 40.sp
        )
        Text(
            text = costaConPlayas.costa.descripcion,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            fontSize = 20.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.playasico),
                contentDescription = "Ver playas",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 20.dp)
                    .clickable {
                        viewModel.prepararPlayasScreen()
                        navController.navigate(AppScreens.PlayasScreen.route)
                    }
            )

            Text(
                text = "Playas...",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.azul_oscuro),
                modifier = Modifier
                    .clickable {
                        viewModel.prepararPlayasScreen()
                        navController.navigate(AppScreens.PlayasScreen.route)
                    }
            )
        }
    }
}



