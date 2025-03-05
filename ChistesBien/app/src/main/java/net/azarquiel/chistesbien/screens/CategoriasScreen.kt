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
import net.azarquiel.chistesbien.navigation.AppScreens
import net.azarquiel.chistesbien.viewmodel.MainViewModel

lateinit var viewModelo: MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriasScreen(navController: NavController, viewModel: MainViewModel) {
    viewModelo = viewModel
    Scaffold(
        topBar = { TopAppBar(title = { Text("Categorias") }) },
        content = { paddingValues -> CategoriasContent(navController, paddingValues) }
    )
}

@Composable
fun CategoriasContent(
    navController: NavController,
    paddingValues: PaddingValues
) {
    val categorias = viewModelo.categorias.observeAsState(listOf())
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        LazyColumn {
            items(categorias.value) {
                Text(
                    text = it.nombre,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "categoria",
                                it
                            )
                            navController.navigate(AppScreens.ChistesScreen.route)
                        }
                )
            }
        }
    }
}