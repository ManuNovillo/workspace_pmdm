package net.azarquiel.retrofit.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import net.azarquiel.retrofit.R
import net.azarquiel.retrofit.model.Comentario
import net.azarquiel.retrofit.model.Gafa
import net.azarquiel.retrofit.model.Marca
import net.azarquiel.retrofit.model.Usuario
import net.azarquiel.retrofit.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun GafasDetailScreen(navController: NavController, viewModel: MainViewModel) {
    Log.d("MANU", "ANTES")
    val gafa = navController.previousBackStackEntry?.savedStateHandle?.get<Gafa>("gafa")
    Log.d("MANU", "DESPUES")
    gafa?.let {
        val usuario by viewModel.usuario.observeAsState(null)
        viewModel.loadComentariosGafa(it)
        Scaffold(
            topBar = { GafasDetailTopBar(viewModel) },
            content = { paddingValues ->
                GafasDetailContent(
                    it,
                    usuario,
                    navController,
                    viewModel,
                    paddingValues
                )
            },
            floatingActionButton = { GafasDetailFAB(viewModel, usuario) },
            floatingActionButtonPosition = FabPosition.EndOverlay
        )
    }
}

@Composable
fun GafasDetailFAB(viewModel: MainViewModel, usuario: Usuario?) {
    val context = LocalContext.current
    FloatingActionButton(
        containerColor = colorResource(R.color.morado),
        onClick = {
            if (usuario == null)
                Toast.makeText(context, "Debes iniciar sesión", Toast.LENGTH_SHORT).show()
            else {
                viewModel.setComentarioDialog(true)
            }
        }
    ) {
        Icon(
            imageVector = Icons.Default.Create,
            contentDescription = "Crear comentario",
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GafasDetailTopBar(viewModel: MainViewModel) {
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
                Text(text = "Detail")
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
fun GafasDetailContent(
    gafa: Gafa,
    usuario: Usuario?,
    navController: NavController,
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val comentarios by viewModel.comentarios.observeAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.azuloscuro))
            .padding(paddingValues)
    ) {
        ComentarioDialog(gafa, usuario, viewModel)
        GafaCard(gafa, navController)
        LazyColumn {
            items(comentarios) { comentario ->
                ComentarioCard(comentario)
            }
        }
    }
}


@Composable
fun GafaCard(gafa: Gafa, navController: NavController) {
    val marca: Marca? = navController.previousBackStackEntry?.savedStateHandle?.get<Marca>("marca")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 3.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.azulclaro),
            /*contentColor = colorResource(R.color.azuloscuro),*/
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                model = "http://www.ies-azarquiel.es/paco/apigafas/img/gafas/${gafa.imagen}",
                contentDescription = gafa.nombre,
                contentScale = ContentScale.Fit,
            )
            Text(
                text = gafa.nombre,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = gafa.precio
                )

                AsyncImage(
                    model = "http://www.ies-azarquiel.es/paco/apigafas/img/marcas/${marca?.imagen}",
                    contentDescription = marca?.nombre,
                    contentScale = ContentScale.Crop,
                )
            }

        }
    }
}

@Composable
fun ComentarioCard(comentario: Comentario) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp, 3.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.azulclaro),
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = comentario.fecha
                )
                Text(
                    text = comentario.nick
                )
            }
            Text(
                text = comentario.comentario,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ComentarioDialog(gafa: Gafa, usuario: Usuario?, viewModel: MainViewModel) {
    val context = LocalContext.current
    val openComentarioDialog = viewModel.openComentarioDialog.observeAsState(false)
    var comentarioText by remember { mutableStateOf("") }
    if (openComentarioDialog.value) {
        AlertDialog(
            title = { Text(text = "Add comment") },
            text = {
                Column {
                    TextField(
                        modifier = Modifier.padding(bottom = 30.dp),
                        value = comentarioText,
                        onValueChange = {
                            comentarioText = it
                        },
                        label = { Text("Comentario") },
                        placeholder = { Text("Comment") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Create,
                                contentDescription = "Comentario"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )
                }
            },
            onDismissRequest = {  // Si pulsamos fuera cierra
                viewModel.setComentarioDialog(false)
            },
            confirmButton = {
                Button(
                    onClick = {
                        val fechaDate = Date()
                        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        val fecha: String = sdf.format(fechaDate)
                        Log.d("MANU", "TEXTO ${comentarioText}")
                        Log.d("MANU", "ID ${usuario!!.id}")
                        val comentario = Comentario(
                            id = "1",
                            comentario = comentarioText,
                            fecha = fecha,
                            gafa = gafa.id,
                            usuario = usuario.id
                        )
                        viewModel.saveComentario(gafa, comentario)
                        comentarioText = ""
                        viewModel.setComentarioDialog(false)
                        Toast.makeText(context, "Comentario guardado", Toast.LENGTH_SHORT).show()
                    })
                { Text("Ok") }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.setComentarioDialog(false) })
                { Text("Cancel") }
            })
    }
}

