package net.azarquiel.firebasechat.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.azarquiel.firebasechat.model.Post
import net.azarquiel.firebasechat.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = { MainTopAppBar() },
        content = {paddingValues -> MainContent(viewModel, paddingValues) },
        floatingActionButton = { MainFab(viewModel) }
    )
}

@Composable
fun MainFab(viewModel: MainViewModel) {
    FloatingActionButton(
        content = { Text("+") },
        onClick = { viewModel.openDialog.value = true }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar() {
    TopAppBar(
        title = { Text("Firebase Chat") }
    )
}

@Composable
fun MainContent(viewModel: MainViewModel, paddingValues: PaddingValues) {
    val mensajes = viewModel.mensajes
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        Dialogo(viewModel)
        LazyColumn {
            items(mensajes) { post ->
                CardMensaje(post)
            }
        }
    }
}

@Composable
fun CardMensaje(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = post.user,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Text(
                text = post.msg,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun Dialogo(viewModel: MainViewModel) {
    val openDialog by viewModel.openDialog
    if (openDialog) {
        val texto = remember { mutableStateOf("") }
        AlertDialog(
            title = { Text("Insertar") },
            text = {
                Column {
                    TextField(
                        value = texto.value,
                        onValueChange = { texto.value = it }
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.openDialog.value = false
                    }
                ) {
                    Text("Salir")
                }
            },
            onDismissRequest = { viewModel.openDialog.value = false },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.addPost(Post("manuel", texto.value))
                        viewModel.openDialog.value = false
                        texto.value = ""
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}