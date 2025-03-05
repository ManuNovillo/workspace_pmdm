package net.azarquiel.chistesbien.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import net.azarquiel.chistesbien.model.Categoria
import net.azarquiel.chistesbien.model.Chiste
import net.azarquiel.chistesbien.navigation.AppScreens
import net.azarquiel.chistesbien.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChisteDetailScreen(navController: NavController, viewModel: MainViewModel) {
    val chiste = navController.previousBackStackEntry?.savedStateHandle?.get<Chiste>("chiste")
    Scaffold(
        topBar = { TopAppBar(title = { Text("Categorias") }) },
        content = { paddingValues -> ChistesDetailContent(navController, paddingValues) }
    )
}

@Composable
fun ChistesDetailContent(
    navController: NavController,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        AlertDialog_Login(viewModelo)
        Button(
            onClick = {
                viewModelo.setDialog(true)
            }
        ) { Text("Hola") }
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


@Composable
fun AlertDialog_Login(viewModel: MainViewModel) {
    val context = LocalContext.current
    val openDialog = viewModel.openDialog.observeAsState(false)
    var nick by remember { mutableStateOf("") }
    if (openDialog.value) {
        AlertDialog(
            title = { Text(text = "Login/Register") },
            text = {
                Column {
                    TextField(
                        modifier = Modifier.padding(bottom = 30.dp),
                        value = nick,
                        onValueChange = {
                            nick = it
                        },
                        label = { Text("Nick") },
                        placeholder = { Text("Nick") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Producto"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )
                }
            },
            onDismissRequest = {  // Si pulsamos fuera cierra
                viewModel.setDialog(false)
            },
            confirmButton = {
                Button(
                    onClick = {
                        val chiste = Chiste(nick, "1", "2")
                        Log.d("MANU", chiste.toString())
                        viewModel.saveChiste(chiste)
                    })
                { Text("Ok") }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.setDialog(false) })
                { Text("Cancel") }
            })

    }
}
