package com.manuel.likesViewModel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuel.likesViewModel.ui.theme.LikesViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LikesViewModelTheme {
                MainScreen(MyViewModel(), this)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MyViewModel, mainActivity: MainActivity) {
    Scaffold(
        topBar = { CustomTopBar() },
        content = { padding -> CustomContent(padding, viewModel, mainActivity) },
        modifier = Modifier.padding(0.dp, 25.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Juego de Adivinanza",
            )
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        ),
        windowInsets = WindowInsets(2.dp)

    )
}

@Composable
fun ColumnRow(viewModel: MyViewModel, mainActivity: MainActivity) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val arrayText = arrayOf("<<<", "<<", "<", ">", ">>", ">>>")
        val arrayValues = arrayOf(-10, -5, -1, 1, 5, 10)
        for (i in 0..5) {
            Button(
                modifier = Modifier.width(65.dp),
                onClick = {
                    viewModel.incrementNum(arrayValues[i])
                    viewModel.incrementIntentos()
                    val result = viewModel.checkRandom(viewModel.num.value!!)
                    /*when (result) {
                        -1 -> {
                            Toast.makeText(mainActivity, "Te has quedado corto", Toast.LENGTH_SHORT).show()
                        }

                        0 -> {
                            Toast.makeText(mainActivity, "Has acertado", Toast.LENGTH_LONG).show()
                            mainActivity.finish()
                        }

                        1 -> {
                            Toast.makeText(mainActivity, "Te has pasado", Toast.LENGTH_SHORT).show()
                        }
                    }*/
                    Toast.makeText(mainActivity, result, Toast.LENGTH_SHORT).show()
                },
                content = {
                    Text(text = arrayText[i], fontSize = 10.sp)
                }
            )
        }
    }
}

@Composable
fun CustomContent(padding: PaddingValues, viewModel: MyViewModel, mainActivity: MainActivity) {
    CustomTopBar2(viewModel)
    Column(
        // Modificadores de estilo de la columna
        modifier = Modifier
            // Ocupar todo el espacio disponible
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.SpaceAround,
        // Contenido de la aplicación
        content = {
            ColumnNumber(viewModel)
            ColumnRow(viewModel, mainActivity)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun ColumnNumber(viewModel: MyViewModel) {
    val num by viewModel.num.observeAsState(0)
    Text(
        text = num.toString(),
        fontSize = 40.sp
    )
}

@Composable
fun CustomTopBar2(viewModel: MyViewModel) {
    val intentos by viewModel.intentos.observeAsState(0)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 22.dp),
        horizontalArrangement = Arrangement.End,
        content = {
            Text(
                text = "Intentos $intentos",
                fontSize = 20.sp
            )
        }
    )
}

