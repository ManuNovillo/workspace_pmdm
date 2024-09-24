package com.example.likes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.likes.ui.theme.LikesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LikesTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreen() {
    Scaffold(
        // Barra superior
        topBar = { CustomTopBar() },
        content = { padding ->
            CustomContent(padding)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
// Título de la barra superior
        title = { Text(text = "Likes") },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.background),
            titleContentColor = colorResource(R.color.foreground)
        )
    )
}

@Composable
fun CustomContent(padding: PaddingValues) {
    Column(
        // Modificadores de estilo de la columna
        modifier = Modifier
            // Ocupar todo el espacio disponible
            .fillMaxSize()
            .padding(padding),
        // Contenido de la aplicación
        content = {
            Content()
        }
    )
}


@Composable
fun Content() {
    var likes by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyRow(likes)
        MyButton(
            buttonText = "Like",
            onClickFunction = {
                likes++
            }
        )
    }
}

@Composable
fun MyRow(likes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        MyImage()
        Text(
            text = likes.toString(),
            color = Color.Red,
            fontSize = 100.sp,
            modifier = Modifier.padding(start = 10.dp),
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.heart),
        contentDescription = "Heart",
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun MyButton(buttonText: String, onClickFunction: () -> Unit) {
    Button(
        onClick = onClickFunction,
        content = {
            Text(text = buttonText)
        }
    )
}

