package net.azarquiel.examenmanuelnovillo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.azarquiel.examenmanuelnovillo.R
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
        title = { Text(text = "Comunidades") },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.purple_700),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun MainContent(
    padding: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(colorResource(R.color.purple_700))
    ) {
        LazyColumn {
            items(listOf("")) { comunidad ->
                MainCard(comunidad, viewModel, navController)
            }
        }
    }
}

@Composable
fun MainCard(
    any: Any,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        colors = cardColors(

        ),
        onClick = {

        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}



