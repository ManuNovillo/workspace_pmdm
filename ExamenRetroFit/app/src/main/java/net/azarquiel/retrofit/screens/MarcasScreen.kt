package net.azarquiel.retrofit.screens

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import net.azarquiel.retrofit.R
import net.azarquiel.retrofit.model.Marca
import net.azarquiel.retrofit.navigation.AppScreens
import net.azarquiel.retrofit.viewmodel.MainViewModel

@Composable
fun MarcasScreen(navController: NavController, viewModel: MainViewModel) {
    val texto = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = { MarcasTopBar(texto, viewModel) },
        content = { paddingValues -> MarcasContent(navController, viewModel, texto, paddingValues) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarcasTopBar(texto: MutableState<TextFieldValue>, viewModel: MainViewModel) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Marcas"
                )
                FiltroTextField(
                    texto
                )
                Icon(
                    modifier = Modifier
                        .clickable {
                            viewModel.setLoginDialog(true)
                        },
                    imageVector = Icons.Default.Person,
                    contentDescription = "Search Icon"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.morado),
            titleContentColor = Color.White
        )
    )
}



@Composable
fun FiltroTextField(texto: MutableState<TextFieldValue>) {
    TextField(
        value = texto.value,
        onValueChange = { value ->
            texto.value = value
        },
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color.DarkGray, RoundedCornerShape(16.dp)),
        placeholder = {
            Text(text = "Search here...")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(R.color.azulclaro),
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

@Composable
fun MarcasContent(
    navController: NavController,
    viewModel: MainViewModel,
    texto: MutableState<TextFieldValue>,
    paddingValues: PaddingValues
) {
    val marcas by viewModel.marcas.observeAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .background(colorResource(R.color.azuloscuro))
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LoginDialog(viewModel)
        LazyColumn {
            val marcasFiltradas = marcas.filter { marca ->
                marca.nombre.contains(texto.value.text, true)
            }
            itemsIndexed(marcasFiltradas) {i, marca ->
                if (i == 0)
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(),
                        painter = painterResource(R.drawable.header),
                        contentDescription = "Header",
                        contentScale = ContentScale.Crop
                    )
                MarcaCard(marca, navController)
            }
        }
    }
}

@Composable
fun MarcaCard(marca: Marca, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.azulclaro),
        ),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("marca", marca)
            navController.navigate(AppScreens.GafasScreen.route)
        }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            model = "http://www.ies-azarquiel.es/paco/apigafas/img/marcas/${marca.imagen}",
            contentDescription = marca.nombre,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun LoginDialog(viewModel: MainViewModel) {
    val context = LocalContext.current
    val openDialog = viewModel.openLoginDialog.observeAsState(false)
    var nick by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    if (openDialog.value) {
        AlertDialog(
            title = { Text(text = "Login/Register") },
            text = {
                Column{
                    TextField(
                        modifier = Modifier.padding(bottom = 30.dp),
                        value = nick,
                        onValueChange = {
                            nick = it
                        },
                        label = { Text("Nick") },
                        placeholder = { Text("Nick") },
                        leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = "Producto") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )
                    TextField(
                        value = pass,
                        onValueChange = {
                            pass = it
                        },
                        label = { Text("Password") },
                        placeholder = { Text("Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )
                }},
            onDismissRequest = {  // Si pulsamos fuera cierra
                viewModel.setLoginDialog(false)
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (nick.isEmpty() || pass.isEmpty()) {
                            Toast.makeText( context, "required fields", Toast.LENGTH_LONG).show()
                        }
                        else {
                            viewModel.login(nick,pass)
                            nick = ""
                            pass = ""
                            viewModel.setLoginDialog(false)
                        }})
                { Text("Ok") }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.setLoginDialog(false) })
                { Text("Cancel") }
            }
        )
    }
}
