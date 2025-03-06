package net.azarquiel.retrofit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import net.azarquiel.retrofit.R
import net.azarquiel.retrofit.model.Gafa
import net.azarquiel.retrofit.model.Marca
import net.azarquiel.retrofit.navigation.AppScreens
import net.azarquiel.retrofit.viewmodel.MainViewModel

@Composable
fun GafasScreen(navController: NavController, viewModel: MainViewModel) {
    val marca = navController.previousBackStackEntry?.savedStateHandle?.get<Marca>("marca")
    marca?.let {
        viewModel.loadGafas(it)
        Scaffold(
            topBar = { GafasTopBar(marca, viewModel) },
            content = { paddingValues -> GafasContent(marca, navController, viewModel, paddingValues) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GafasTopBar(marca: Marca, viewModel: MainViewModel) {
    val usuario by viewModel.usuario.observeAsState(initial = null)
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = marca.nombre)
                Text(text = usuario?.nick ?: "")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.morado),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun GafasContent(
    marca: Marca,
    navController: NavController,
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val gafas by viewModel.gafas.observeAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.azuloscuro))
            .padding(paddingValues)
    ) {
        LazyColumn {
            items(gafas) { gafa ->
                GafasCard(gafa, marca, navController)
            }
        }
    }
}

@Composable
fun GafasCard(gafa: Gafa, marca: Marca, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 3.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.azulclaro),
        ),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("gafa", gafa)
            navController.currentBackStackEntry?.savedStateHandle?.set("marca", marca)
            navController.navigate(AppScreens.GafasDetailScreen.route)
        }
    ) {
        Row(
           modifier = Modifier
               .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f),
                model = "http://www.ies-azarquiel.es/paco/apigafas/img/gafas/${gafa.imagen}",
                contentDescription = gafa.nombre,
                contentScale = ContentScale.Crop,
            )

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                text = gafa.nombre,
                textAlign = TextAlign.End
            )
        }
    }

}
