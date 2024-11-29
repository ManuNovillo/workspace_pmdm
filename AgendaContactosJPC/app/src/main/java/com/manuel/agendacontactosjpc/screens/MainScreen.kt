package com.manuel.agendacontactosjpc.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuel.agendacontactosjpc.R
import com.manuel.agendacontactosjpc.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = { TopBar() },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = { BotonFlotante(viewModel) },
        content = { padding -> MainContent(padding, textState, viewModel) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Agenda de contactos",
                fontSize = 20.sp
            )
        },
        modifier = Modifier
            .background(colorResource(R.color.purple_700))

    )
}

@Composable
fun BotonFlotante(viewModel: MainViewModel) {
    FloatingActionButton(
        onClick = { viewModel.setDialog(true) },
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Boton",
            tint = Color.White
        )
    }
}

@Composable
fun MainContent(
    padding: PaddingValues,
    textState: MutableState<TextFieldValue>,
    viewModel: MainViewModel
) {
    Dialogo(viewModel)
    val amigos = viewModel.amigos
    Column(
        modifier = Modifier
            .padding(padding)
    ) {
        SearchView(textState)
        LazyColumn {
            val amigosFiltrados = amigos
            items(amigosFiltrados) { amigo ->
                Text(text = amigo.nombre)
            }
        }
    }
}

@Composable
fun Dialogo(viewModel: MainViewModel) {
    val openDialog by viewModel.openDialog
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.setDialog(false) },
            title = { Text(text = "Añadir amigo") },
            text = {
                val textoNombre = remember { mutableStateOf(TextFieldValue("")) }
                val textoCorreo = remember { mutableStateOf(TextFieldValue("")) }
                val textoTelefono = remember { mutableStateOf(TextFieldValue("")) }
                Column {
                    TextField(
                        value = textoNombre.value,
                        onValueChange = {textoNombre.value = it},
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Face, "nombre")
                        }
                    )

                    TextField(
                        value = textoCorreo.value,
                        onValueChange = {textoCorreo.value = it},
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Email, "correo")
                        }
                    )

                    TextField(
                        value = textoCorreo.value,
                        onValueChange = {textoCorreo.value = it},
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(Icons.Default.Phone, "telefono")
                        }
                    )
                }

            }, confirmButton = {
                Button(
                    onClick = { viewModel.setDialog(false) })
                {
                    Text("Confirm Button")
                }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.setDialog(false) })
                {
                    Text("Dismiss Button")
                }
            }
        )
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
            Text(text = "Buscar amigo...")
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



