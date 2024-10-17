import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuel.juegosumasjpc.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = { CustomTopBar() },
        content = { padding ->
            CustomContent(padding, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text(text = "Addition Numbers") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    AlertDialogSample(viewModel)

    val primerNum by viewModel.primerNum.observeAsState("")
    val segundoNum by viewModel.segundoNum.observeAsState("")
    val randomNum by viewModel.randomNum.observeAsState(0)
    val aciertos by viewModel.aciertos.observeAsState(0)
    val intentos by viewModel.intentos.observeAsState(0)
    val backgroundColor by viewModel.backgroundColor.observeAsState(Color.White)

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Row(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
                .weight(1F),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Aciertos: $aciertos",
                fontSize = 24.sp,
            )
            Text(
                text = "Intentos: $intentos",
                fontSize = 24.sp,
            )
        }
        Row(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
                .weight(2F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = randomNum.toString(),
                fontSize = 140.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
                .weight(2F)
                .background(backgroundColor),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = primerNum,
                fontSize = 100.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "+",
                modifier = Modifier.padding(horizontal = 10.dp),
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = segundoNum,
                fontSize = 100.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth()
                .weight(4F),
            verticalArrangement = Arrangement.Center
        ) {
            val botones = Array(3) { i -> Array(3) { j -> (i * 3 + j + 1) } }
            // botones = [[1,2,3],[4,5,6],[7,8,9]] tabla de 3x3 con los valores dentro
            botones.forEach { fila ->
                Row(
                    modifier = Modifier
                        .padding(1.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                )
                {
                    fila.forEach { item ->
                        Boton(item, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Boton(n: Int, viewModel: MainViewModel) {
    Button(
        onClick = {
            viewModel.onButtonClick(n)
        },
        contentPadding = PaddingValues(30.dp, 10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "$n",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AlertDialogSample(viewModel: MainViewModel) {
    val time by viewModel.time.observeAsState("")
    val intentos by viewModel.intentos.observeAsState(0)
    val aciertos by viewModel.aciertos.observeAsState(0)
    val context = LocalContext.current

    MaterialTheme {
        Column {
            val openDialog by viewModel.openDialog.observeAsState(false)
            Button(onClick = {

            })
            { Text("Click me") }
            if (openDialog) {
                AlertDialog(
                    onDismissRequest = {  // Si pulsamos fuera

                    },
                    title = {
                        Text(text = "Has acabado")
                    },
                    text = {
                        Text("$aciertos/$intentos en $time segundos")
                    },
                    confirmButton = {
                        Button(
                            onClick = { viewModel.restartGame() })
                        {
                            Text("Continuar")
                        }

                    },
                    dismissButton = {
                        Button(
                            onClick = { viewModel.mainActivity.finish() })
                        {
                            Text("Salir")
                        }
                    }
                )
            }
        }
    }
}

