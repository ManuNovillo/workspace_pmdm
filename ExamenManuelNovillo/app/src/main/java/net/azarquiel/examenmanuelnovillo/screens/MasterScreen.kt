package net.azarquiel.examenmanuelnovillo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import net.azarquiel.examenmanuelnovillo.R
import net.azarquiel.examenmanuelnovillo.model.CostaConPlayas
import net.azarquiel.examenmanuelnovillo.navigation.AppScreens
import net.azarquiel.examenmanuelnovillo.viewmodel.MainViewModel

@Composable
fun MasterScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { MainTopBar() },
        content = { padding -> MainContent(padding, viewModel, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        title = { Logo() },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.azul_oscuro)
        )
    )
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(R.drawable.andalucia_logo),
        contentDescription = "logo",
        modifier = Modifier
            .size(300.dp)
    )
}

@Composable
fun MainContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val costasConPlayas by viewModel.costas.observeAsState(emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(colorResource(R.color.azul_oscuro))
    ) {
        LazyColumn {
            items(costasConPlayas) { costaConPlayas ->
                CostaCard(costaConPlayas, viewModel, navController)
            }
        }
    }
}

@Composable
fun CostaCard(
    costaConPlayas: CostaConPlayas,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        colors = cardColors(
            containerColor = colorResource(R.color.azul_claro)
        ),
        onClick = {
            viewModel.prepararDetailScreen(costaConPlayas)
            navController.navigate(AppScreens.DetailScreen.route)
        }
    ) {
        Column {
            AsyncImage(
                model = costaConPlayas.costa.imagen,
                contentDescription = costaConPlayas.costa.nombre,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = costaConPlayas.costa.nombre,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                color = colorResource(R.color.azul_oscuro),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



