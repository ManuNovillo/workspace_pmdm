package net.azarquiel.examenmanuelnovillo.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import net.azarquiel.examenmanuelnovillo.R
import net.azarquiel.examenmanuelnovillo.viewmodel.MainViewModel
import androidx.compose.ui.text.input.TextFieldValue as TextFieldValue1

@Composable
fun DetailScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { DetailTopBar() },
        content = { padding -> DetailContent(padding, viewModel, navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar() {
    TopAppBar(
        title = { Text(text = "Comunidades") },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.purple_700),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun DetailContent(
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
                DetailCard(comunidad, viewModel, navController)
            }
        }
    }
}

@Composable
fun SearchView(
    state: MutableState<TextFieldValue1>
) {
    TextField(
        value = state.value,
        onValueChange = {value->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
        placeholder = {
            Text(text = "Search here...")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = Color.White,
        ),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )
}


@Composable
fun DetailCard(
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
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl("")
                }
            }, update = {
                it.loadUrl("")
            })
        }
    }
}

fun crearTostada(texto: String, viewModel: MainViewModel) {
    Toast.makeText(viewModel.mainActivity, texto, Toast.LENGTH_SHORT).show()
}

@Composable
fun BotonFlotante() {
    FloatingActionButton(
        shape = RoundedCornerShape(100),
        containerColor = colorResource(R.color.teal_200),
        contentColor = MaterialTheme.colorScheme.secondary,
        onClick = {

        }) {
        Image(
            painterResource(android.R.drawable.star_big_on),
            contentDescription = "Fav  Image",
            modifier = Modifier
                .size(30.dp)
                .background(colorResource(R.color.black))
        )
    }
}