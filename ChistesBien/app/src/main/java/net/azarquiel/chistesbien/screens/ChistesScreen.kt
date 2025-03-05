package net.azarquiel.chistesbien.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.azarquiel.chistesbien.model.Categoria
import net.azarquiel.chistesbien.model.Chiste
import net.azarquiel.chistesbien.navigation.AppScreens
import net.azarquiel.chistesbien.viewmodel.MainViewModel

lateinit var chistes: List<Chiste>

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChistesScreen(navController: NavController, viewModel: MainViewModel) {
    val categoria = navController.previousBackStackEntry?.savedStateHandle?.get<Categoria>("categoria")
    categoria?.let {
        viewModel.getChistes(it.id)
        chistes = viewModel.chistes.observeAsState(listOf()).value
        Scaffold(
            topBar = { TopAppBar(title = { Text("Categorias") }) },
            content = { paddingValues -> ChistesContent(navController, paddingValues) }
        )
    }
}

@Composable
fun ChistesContent(
    navController: NavController,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        LazyColumn {
            items(chistes) {
                Text(
                    text = it.contenido,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "chiste",
                                it
                            )
                            navController.navigate(AppScreens.ChisteDetailScreen.route)
                        }
                )
            }
        }
    }
}